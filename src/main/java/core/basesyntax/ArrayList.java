package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int arraySize = DEFAULT_SIZE;
    private int size;
    private Object[] values;

    public ArrayList() {
        values = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int newCapacity) {
        values = new Object[newCapacity];
    }

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        resize();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            values[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("mes");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        Object oldValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        resize();
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            Object oldValue = values[index];
            System.arraycopy(values,index + 1, values, index, size - index - 1);
            values[size - 1] = null;
            size--;
            return (T) oldValue;
        }
        if (index == -1) {
            throw new NoSuchElementException("There is not element in an array");
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
            Object[] newValues = new Object[size * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i] == value || values[i] != null && values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
