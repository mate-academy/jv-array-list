package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[START_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            extendArray();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == array.length) {
            extendArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        checkBound(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkBound(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBound(index);
        T deletedElement = (T) array[index];
        if (index != array.length - 1) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        array[--size] = null;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int index = getIndexByValue(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void checkBound(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void extendArray() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] copiedElements = new Object[newCapacity];
        System.arraycopy(array, 0, copiedElements, 0, size);
        array = copiedElements;
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value || array[i] != null && array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
