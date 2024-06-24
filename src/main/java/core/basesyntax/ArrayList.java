package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;

    private int size;

    private Object[] dataArray;

    public ArrayList() {
        this.dataArray = new Object[START_CAPACITY];
        this.size = 0;
    }

    private boolean sizeCheck() {
        return size == dataArray.length;
    }

    private void grow(int minCapacity) {
        int oldCapacity = dataArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        dataArray = Arrays.copyOf(dataArray, newCapacity);
    }

    @Override
    public void add(T value) {
        if (sizeCheck()) {
            grow(size + 1);
        }
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (sizeCheck()) {
            grow(size + 1);
        }
        System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
        dataArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }

        int newSize = size + list.size();
        if (newSize > dataArray.length) {
            grow(newSize);
        }

        for (int i = 0; i < list.size(); i++) {
            dataArray[size + i] = list.get(i);
        }

        size = newSize;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        T removedValue = (T) dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (dataArray[i] == null) {
                    final T removedElement = (T) dataArray[i];
                    for (int j = i; j < size - 1; j++) {
                        dataArray[j] = dataArray[j + 1];
                    }
                    dataArray[size - 1] = null;
                    size--;
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(dataArray[i])) {
                    final T removedElement = (T) dataArray[i];
                    for (int j = i; j < size - 1; j++) {
                        dataArray[j] = dataArray[j + 1];
                    }
                    dataArray[size - 1] = null;
                    size--;
                    return removedElement;
                }
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
