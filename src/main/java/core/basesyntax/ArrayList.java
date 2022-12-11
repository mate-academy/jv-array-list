package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String SCAN_INDEX_MESSAGE = "Invalid index";
    private static final String INDEX_EQUALS_SIZE = "Index equals with size";
    private Object[] elementArray;
    private int size;

    public ArrayList() {
        this.elementArray = new Object[DEFAULT_CAPACITY];
    }

    public Object[] grow() {
        int newLength = size * 3 / 2;
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementArray, 0, newArray, 0, size);
        return elementArray = newArray;
    }

    public Object[] cutBefore(int index) {
        Object[] cutArray = new Object[index];
        System.arraycopy(elementArray, 0, cutArray, 0, index);
        return cutArray;
    }

    public Object[] cutAfter(int index) {
        Object[] cutArray = new Object[size - index];
        System.arraycopy(elementArray, index, cutArray, 0, size - index);
        return cutArray;
    }

    public void scanIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(SCAN_INDEX_MESSAGE);
        }
    }

    public void indexEqualsSize(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EQUALS_SIZE);
        }
    }

    @Override
    public void add(T value) {
        if (elementArray.length == size) {
            grow();
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (elementArray.length == size) {
            grow();
        }
        scanIndex(index);
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index);
        elementArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        scanIndex(index);
        indexEqualsSize(index);
        return (T) elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        scanIndex(index);
        indexEqualsSize(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        scanIndex(index);
        indexEqualsSize(index);
        final T removeElement = (T) elementArray[index];
        Object[] beforeArray = cutBefore(index);
        Object[] afterArray = cutAfter(index + 1);
        Object[] newArray = new Object[elementArray.length];
        System.arraycopy(beforeArray, 0, newArray, 0, beforeArray.length);
        System.arraycopy(afterArray, 0, newArray, index, afterArray.length);
        elementArray = newArray;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int beforeSize = size;
        for (int i = 0; i < size; i++) {
            if (elementArray[i] == null && element == null
                    || elementArray[i] != null && elementArray[i].equals(element)) {
                remove(i);
                break;
            }
        }
        if (beforeSize == size) {
            throw new NoSuchElementException("element exception");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void setElementArray(Object[] elementArray) {
        this.elementArray = elementArray;
    }
}
