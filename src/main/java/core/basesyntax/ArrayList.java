package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int defaultSize = 10;
    private Object[] defaultNewCapacity;
    private T[] elementArray;
    private int size;

    public ArrayList() {
        elementArray = (T[]) new Object[defaultSize];
    }

    public ArrayList(int capacity) {
        elementArray = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        grow();
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            checkIndex(index);
        }
        final int s = size;
        if (s == this.elementArray.length) {
            grow();
        }
        System.arraycopy(elementArray, index, elementArray, index + 1, s - index);
        elementArray[index] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() <= 0) {
            throw new IndexOutOfBoundsException("list " + list.size()
                    + " out of bounds for length " + size);
        }
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = get(index);
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        int index = findIndexElement(element);
        if (index >= size || index < 0) {
            throw new NoSuchElementException("Index " + index
                    + " out of bounds for length " + size);
        }
        T result = elementArray[index];
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (elementArray.length == size) {
            defaultNewCapacity = new Object[elementArray.length * 2];
            System.arraycopy(elementArray, 0,
                    defaultNewCapacity, 0, size);
            elementArray = (T[]) defaultNewCapacity;
        }
    }

    private int findIndexElement(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elementArray[i] == null) {
                return i;
            } else if (element != null && element.equals(elementArray[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }
}

