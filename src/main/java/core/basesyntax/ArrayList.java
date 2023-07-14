package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] dataArray;

    public ArrayList() {
        dataArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == dataArray.length) {
            extensionArray();
        }
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexInclusive(index);
        if (size == dataArray.length) {
            extensionArray();
        }

        if (index != size) {
            System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        }

        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if ((size + list.size()) >= dataArray.length) {
                extensionArray();
            }
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexExclusive(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExclusive(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexExclusive(index);
        T removeArrayIndex = (T) dataArray[index];
        size--;

        if (index != size) {
            System.arraycopy(dataArray,index + 1, dataArray, index, size - index);
        }

        return removeArrayIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (dataArray[i] == element
                    || dataArray[i] != null && dataArray[i].equals((T) element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exception");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    private void extensionArray() {
        Object[] oldArray = dataArray;
        dataArray = (T[]) new Object[oldArray.length + (oldArray.length >> 1)];
        System.arraycopy(oldArray,0, dataArray,0,size);
    }

    private void checkIndexInclusive(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " Size" + size);
        }
    }

    private void checkIndexExclusive(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " Size" + size);
        }
    }
}
