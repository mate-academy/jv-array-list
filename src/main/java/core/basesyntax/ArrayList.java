package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] valueArray;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        valueArray = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        valueArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfArrayFull();
        System.arraycopy(valueArray, index, valueArray, index + 1, size - index);
        valueArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Input list must not be null");
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return valueArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        valueArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = valueArray[index];
        System.arraycopy(valueArray, index + 1, valueArray, index, size - index - 1);
        size--;
        valueArray[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((valueArray[i] == null && element == null)
                    || (valueArray[i] != null && valueArray[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void growIfArrayFull() {
        if (size == valueArray.length) {
            int newCapacity = valueArray.length + valueArray.length / 2;
            T[] biggerArray = (T[]) new Object[newCapacity];
            System.arraycopy(valueArray, 0, biggerArray, 0, size);
            valueArray = biggerArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
