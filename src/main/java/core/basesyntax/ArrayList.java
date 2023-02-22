package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final int EMPTY_SIZE = 0;
    private static final int CAPACITY_DIVISOR = 2;
    private T[] dataElements;
    private int size;

    public ArrayList() {
        dataElements = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        if (arrayIsFull()) {
            grow();
        }
        dataElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add to index " + index);
        }
        if (arrayIsFull()) {
            grow();
        }
        System.arraycopy(dataElements, index, dataElements, index + 1, size++ - index);
        dataElements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkArrayListIndexOutOfBoundsException(index, "get");
        return dataElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkArrayListIndexOutOfBoundsException(index, "set");
        dataElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArrayListIndexOutOfBoundsException(index, "remove");
        T element = dataElements[index];
        System.arraycopy(dataElements, index + 1, dataElements, index, size-- - index - 1);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (dataElements[i] == element
                    || (dataElements[i] != null)
                    && dataElements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no " + element + " Element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    private void grow() {
        int oldCapacity = dataElements.length;
        int newCapacity = oldCapacity + (oldCapacity / CAPACITY_DIVISOR);
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        }
        T[] newDataElement = (T[]) new Object[newCapacity];
        System.arraycopy(dataElements, 0, newDataElement, 0, oldCapacity);
        dataElements = newDataElement;
    }

    private boolean indexIsInvalid(int index) {
        return index < 0 || index >= size;
    }

    private boolean arrayIsFull() {
        return size == dataElements.length;
    }

    private void checkArrayListIndexOutOfBoundsException(int index, String message) {
        if (indexIsInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't " + message + " from index "
                    + index);
        }
    }

}
