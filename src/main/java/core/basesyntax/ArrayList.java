package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] dataArray;

    public ArrayList() {
        this.dataArray = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        addIndexCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(dataArray, index, dataArray, index + 1, dataArray.length - index - 1);
        dataArray[index] = value;
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
        boundsCheck(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        boundsCheck(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        boundsCheck(index);
        final T oldValue = (T) dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, dataArray.length - index - 1);
        dataArray[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(dataArray[i], element)) {
                final T oldValue = (T) dataArray[i];
                System.arraycopy(dataArray, i + 1, dataArray, i, dataArray.length - i - 1);
                dataArray[size - 1] = null;
                size--;
                return oldValue;
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

    private void boundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index, size);
        }
    }

    private void addIndexCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index, size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity >= dataArray.length) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = dataArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        dataArray = Arrays.copyOf(dataArray, newCapacity);
    }
}
