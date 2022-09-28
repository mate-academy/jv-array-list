package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ARRAY_LENGTH = 10;
    private T[] values;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("this index is invalid");
        }
        values = (T[]) new Object[MAX_ARRAY_LENGTH];
    }

    public ArrayList() {
        this(MAX_ARRAY_LENGTH);
    }

    private void resizeArray() {
        if (size == values.length) {
            int sizeNewArray = (int) (values.length * 1.5);
            T[] newArray = (T[]) new Object[(int) (sizeNewArray)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    private void checkIndex(int index, int size) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is incorrect: " + index);
        }
    }

    private void removeValue(int index) {
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        values[size] = null;
    }

    @Override
    public void add(T value) {
        resizeArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        resizeArray();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        resizeArray();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removedValue = values[index];
        removeValue(index);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                index = i;
            }
        }
        if (index < 0) {
            throw new NoSuchElementException("Element " + element + "is not find");
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
