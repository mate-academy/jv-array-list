package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] elementArray;
    private int size;

    public ArrayList() {
        elementArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementArray.length) {
            grow();
        }
        elementArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementArray.length) {
            grow();
        }
        size++;
        checkIndex(index);
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index - 1);
        elementArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int a = 0; a < list.size(); a++) {
            add(list.get(a));
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
        size--;
        T deletedElement = elementArray[index];
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index);
        elementArray[size] = null;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int a = 0; a < size; a++) {
            if (element == elementArray[a] || element != null && element.equals(elementArray[a])) {
                return remove(a);
            }
        }
        throw new NoSuchElementException("element " + element
                + "is missing from the T[] elementArray");
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
        int newCapacity = (int) (elementArray.length * GROW_FACTOR);
        T[] resizedArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementArray, 0, resizedArray, 0, size);
        elementArray = resizedArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is not correction. size = " + size);
        }
    }
}
