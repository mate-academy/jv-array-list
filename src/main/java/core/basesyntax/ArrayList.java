package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private int size = 0;
    private Object[] customArrayList = new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {
        growIfArrayFull();
        customArrayList[size] = value;
        changeSize(1);
    }

    private void growIfArrayFull() {
        if (size == customArrayList.length) {
            int newSize = (int) (customArrayList.length * GROW_COEFFICIENT);
            Object[] biggerArrayList = new Object[newSize];
            System.arraycopy(customArrayList, 0, biggerArrayList, 0, customArrayList.length);
            customArrayList = biggerArrayList;
        }
    }

    private void checkIfIndexValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("DevCap: IndexException");
        }
    }

    private void checkIfIndexValidExclusive(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("DevCap: IndexException");
        }
    }

    private int findValueInArray(T value) {
        for (int i = 0; i < size; i++) {
            // Additional built in check for nulls
            if (Objects.equals(customArrayList[i], value)) {
                return i;
            }
        }
        // Element not found
        return -1;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexValidExclusive(index);
        if (index == size) {
            add(value);
        } else {
            growIfArrayFull();
            System.arraycopy(customArrayList, index, customArrayList, index + 1, size - index);
            customArrayList[index] = value;
            changeSize(1);
        }
    }

    @Override
    public void addAll(List<T> list) {
        // For each is not working because Custom list does not implement iterable interface
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexValid(index);
        // Caution, unchecked cast
        return (T) customArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexValid(index);
        customArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexValid(index);
        T value = get(index);
        System.arraycopy(customArrayList, 0, customArrayList, 0, index);
        // If our element is last, no need to fetch second part of array
        // (there is no second part in fact, it will cause index exception)
        if (index != size() - 1) {
            System.arraycopy(customArrayList, index + 1, customArrayList, index, size - index);
        }
        changeSize(-1);
        return value;
    }

    @Override
    public T remove(T element) {
        int index = findValueInArray(element);
        if (index == -1) {
            throw new NoSuchElementException("DevCap: There is no such element friend");
        } else {
            return remove(index);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void changeSize(int delta) {
        size += delta;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
