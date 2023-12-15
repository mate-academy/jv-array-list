package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int BIT_SHIFT_VALUE = 1;
    private static final int ONE_ELEMENT_SIZE = 1;
    private static final int FIRST_ELEMENT_INDEX = 0;
    private static final int NO_SUCH_ELEMENT_INDEX = -1;
    private static final String NO_SUCH_ELEMENT_MESSAGE = "There is no element"
            + " with index %d in list.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Index %d does not exist in list.";

    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow() {
        data = Arrays.copyOf(data, data.length + (data.length >> BIT_SHIFT_VALUE));
        return data;
    }

    private int getIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element || (data[i] != null && data[i].equals(element))) {
                return i;
            }
        }
        return NO_SUCH_ELEMENT_INDEX;
    }

    private void checkAddByIndex(int index) {
        if (index < FIRST_ELEMENT_INDEX || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_OUT_OF_BOUNDS_MESSAGE, index));
        }
    }

    private void checkGetByIndex(int index) {
        if (index < FIRST_ELEMENT_INDEX || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format(INDEX_OUT_OF_BOUNDS_MESSAGE, index));
        }
    }

    private void checkElementByIndex(int index) {
        if (index < FIRST_ELEMENT_INDEX || index > size) {
            throw new NoSuchElementException(String
                    .format(NO_SUCH_ELEMENT_MESSAGE, index));
        }
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkAddByIndex(index);
        if (size == data.length) {
            data = grow();
        }
        System.arraycopy(data, index, data, index + ONE_ELEMENT_SIZE, size - index);
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
        checkGetByIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkGetByIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedObject = get(index);
        if (size - index > ONE_ELEMENT_SIZE) {
            System.arraycopy(data, index + ONE_ELEMENT_SIZE, data, index, size - index);
        }
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        int elementIndex = getIndexByElement(element);
        checkElementByIndex(elementIndex);
        System.arraycopy(data, elementIndex + 1, data, elementIndex, size - elementIndex);
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == FIRST_ELEMENT_INDEX;
    }
}
