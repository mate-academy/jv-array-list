package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int BIT_SHIFT_VALUE = 1;
    private static final int ONE_ELEMENT_SIZE = 1;
    private static final int EMPTY_LIST_VALUE = 0;
    private static final int FIRST_ELEMENT_INDEX = 0;
    private static final int NO_SUCH_ELEMENT_INDEX = -1;
    private static final String NO_SUCH_ELEMENT_MESSAGE = "There is no such element in list.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "This index does not exist in list.";

    private Object[] data = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    private Object[] grow() {
        data = data.length > EMPTY_LIST_VALUE
                ? Arrays.copyOf(data, data.length + (data.length >> BIT_SHIFT_VALUE))
                : new Object[DEFAULT_CAPACITY];
        return data;
    }

    private int getIndexByElement(T element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element || (data[i] != null && data[i].equals(element))) {
                return i;
            }
        }
        return NO_SUCH_ELEMENT_INDEX;
    }

    private void checkAddByIndex(int index) {
        if (index < EMPTY_LIST_VALUE || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private void checkGetByIndex(int index) {
        if (index < EMPTY_LIST_VALUE || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private void checkElementByIndex(int index) {
        if (index < EMPTY_LIST_VALUE || index > size) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
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
        data[index] = data[index] != null ? value : data[index];
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
        checkElementByIndex(getIndexByElement(element));
        int elementIndex = getIndexByElement(element)
                >= FIRST_ELEMENT_INDEX
                ? getIndexByElement(element) : NO_SUCH_ELEMENT_INDEX;
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
        return size == EMPTY_LIST_VALUE;
    }
}
