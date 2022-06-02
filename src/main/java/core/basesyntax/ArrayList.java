package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int arraySize = DEFAULT_CAPACITY;
    private T[] dataValue;
    private int size;

    public ArrayList() {
        dataValue = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int newCapacity) {
        dataValue = (T[]) new Object[newCapacity];
    }

    @Override
    public void add(T value) {
        resize();
        dataValue[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 & index <= size) {
            resize();
            System.arraycopy(dataValue, index, dataValue, index + 1, size - index);
            dataValue[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index isn't valid.");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            dataValue[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {

        if (index < size && index >= 0) {
            return dataValue[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist.");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist.");
        }
        dataValue[index] = value;

    }

    @Override
    public T remove(int index) {

        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist. ");
        }
        final T oldValue = dataValue[index];
        System.arraycopy(dataValue, index + 1, dataValue, index, size - index - 1);
        dataValue[size - 1] = null;
        size--;
        resize();
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            System.arraycopy(dataValue, index + 1, dataValue, index, size - index - 1);
            dataValue[size--] = null;
            return element;
        }
        if (index == -1) {
            throw new NoSuchElementException("There isn't such element.");
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size >= arraySize) {
            T[] newValues = (T[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(dataValue, 0, newValues, 0, size);
            dataValue = newValues;
        }
    }

    public int indexOf(T valu) {
        for (int i = 0; i < size; i++) {
            if (dataValue[i] == valu || dataValue[i] != null && dataValue[i].equals(valu)) {
                return i;
            }
        }
        return -1;
    }
}
