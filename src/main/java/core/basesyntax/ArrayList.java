package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] listOfElements;
    private int size;

    ArrayList() {
        listOfElements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size < listOfElements.length) {
            listOfElements[size++] = value;
        } else {
            grow(size + 1);
            listOfElements[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        checkLengthForAdd(index);
        grow(size + 1);
        System.arraycopy(listOfElements, index, listOfElements, index + 1, size - index);
        listOfElements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        grow(size + 1);
        int indexOfLastElement = size;
        for (int i = 0; i < list.size(); i++) {
            listOfElements[indexOfLastElement++] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return listOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        listOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeElement = listOfElements[index];
        for (int i = index; i < listOfElements.length - 1; i++) {
            listOfElements[i] = listOfElements[i + 1];
        }
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        T deletedElement;
        for (int i = 0; i < size; i++) {
            if ((listOfElements[i] != null
                    && listOfElements[i].equals(element))
                    || listOfElements[i] == element) {
                deletedElement = listOfElements[i];
                remove(i);
                return deletedElement;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow(int size) {
        if (size > listOfElements.length) {
            int oldSize = listOfElements.length;
            int newSize = oldSize + (oldSize >> 1);
            listOfElements = Arrays.copyOf(listOfElements, newSize);
        }
    }

    private void checkLengthForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invailed index: " + index);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("wrong index: " + index
                    + " for ArrayList size: " + size);
        }
    }
}

