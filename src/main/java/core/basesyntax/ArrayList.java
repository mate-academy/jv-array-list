package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int listSize;
    private T[] data = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        if (listSize == data.length) {
            data = capacityGrow();
        }
        data[listSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > listSize) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: " + index);
        }
        if (listSize == data.length) {
            data = capacityGrow();
        }
        System.arraycopy(data, index, data, index + 1, listSize - index);
        data[index] = value;
        listSize++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = listSize + list.size();
        T[] temporaryArray = (T[]) new Object[newSize];
        int listCounter = 0;
        for (int i = listSize; i < newSize; i++) {
            temporaryArray[i] = list.get(listCounter++);
        }
        listSize = newSize;
        data = temporaryArray;
    }

    @Override
    public T get(int index) {
        indexExceptionCheck(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        indexExceptionCheck(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExceptionCheck(index);
        T removedElement = data[index];
        System.arraycopy(data, index + 1, data, index, listSize - index - 1);
        listSize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < listSize; i++) {
                if (data[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < listSize; i++) {
                if (element.equals(data[i])) {
                    final T removedElement = data[i];
                    for (int j = i; j < listSize - 1; j++) {
                        data[j] = data[j + 1];
                    }
                    data[listSize - 1] = null;
                    listSize--;
                    return removedElement;
                }
            }
        }
        throw new NoSuchElementException("Element was not found: " + element);
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    private void indexExceptionCheck(int index) {
        if (index < 0 || index >= listSize) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: " + index);
        }
    }

    private T[] capacityGrow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity / 2);
        T[] temporaryArray = (T[]) new Object[newCapacity];
        if (listSize >= 0) {
            System.arraycopy(data, 0, temporaryArray, 0, listSize);
        }
        return temporaryArray;
    }
}
