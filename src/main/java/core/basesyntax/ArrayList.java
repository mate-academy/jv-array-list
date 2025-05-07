package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MINIMAL_ARRAY_INDEX = 0;
    private static final double GROW_COEFFICIENT = 1.5;

    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index %d out of bounds for size %d";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "There is no such element: ";

    private Object[] data = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == data.length) {
            data = grow();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddOperation(index);
        if (size == data.length) {
            data = grow();
        }

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForGetOperation(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGetOperation(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedObject = get(index);

        if (size - index > 1) {
            System.arraycopy(data, index + 1, data, index, size - index);
        }

        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((data[i] == null && element == null)
                    || (data[i] != null && data[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == MINIMAL_ARRAY_INDEX;
    }

    private Object[] grow() {
        int oldLength = data.length;
        Object[] newArray = new Object[(int) (oldLength * GROW_COEFFICIENT)];
        System.arraycopy(data, 0, newArray, 0, size);
        return newArray;
    }

    private void checkIndexForGetOperation(int index) {
        if (index < MINIMAL_ARRAY_INDEX || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
        }
    }

    private void checkIndexForAddOperation(int index) {
        if (index < MINIMAL_ARRAY_INDEX || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_OUT_OF_BOUNDS_MESSAGE, index, size));
        }
    }
}
