package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArrayIfNeeded();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        resizeArrayIfNeeded();
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
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index, size);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        array[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index, size);
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        T removedElement;
        int elementIndex = getIndex(element);
        if (elementIndex < 0) {
            throw new NoSuchElementException("Element is not found in the array");
        }
        removedElement = (T) array[elementIndex];
        remove(elementIndex);
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArrayIfNeeded() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length + (array.length / 2)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index, int size) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of the range");
        }
    }

    private int getIndex(T value) {
        for (int i = 0; i < size; i++) {
            if (value != null && value.equals(array[i]) || value == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
