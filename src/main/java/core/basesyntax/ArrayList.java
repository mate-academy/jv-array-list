package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROWTH_MULTIPLIER = 1.5;
    private T[] customArrayList;
    private int size;

    public ArrayList() {
        customArrayList = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        customArrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index);
        if (index == size) {
            add(value);
        } else {
            growIfArrayIsFull();
            System.arraycopy(customArrayList, index, customArrayList, index + 1, size - index);
            customArrayList[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValidationInclusive(index);
        return customArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidationInclusive(index);
        customArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidationInclusive(index);
        T value = get(index);
        if (index == size - 1) {
            System.arraycopy(customArrayList, 0, customArrayList, 0, index);
        } else {
            System.arraycopy(customArrayList, index + 1, customArrayList, index, size - index);
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = findElementIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element");
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

    private void growIfArrayIsFull() {
        if (size == customArrayList.length) {
            int newSize = (int) (customArrayList.length * GROWTH_MULTIPLIER);
            T[] newCustomArrayList = (T[]) new Object[newSize];
            System.arraycopy(customArrayList, 0, newCustomArrayList, 0, customArrayList.length);
            customArrayList = newCustomArrayList;
        }
    }

    private void indexValidation(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for size " + size);
        }
    }

    private void indexValidationInclusive(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for size " + size);
        }
    }

    private boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    private int findElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (equals(customArrayList[i], element)) {
                return i;
            }
        }
        return -1;
    }

}
