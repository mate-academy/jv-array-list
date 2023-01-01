package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] values;
    private int size;
    private int arraySize = DEFAULT_SIZE;
    private T removedObject;

    public ArrayList() {
        values = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int initialCapacity) {
        values = new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T object) {
        resize();
        values[size] = object;
        size++;
    }

    @Override
    public void add(T object, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound!");
        }
        resize();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = object;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty() || list.size() == 0) {
            return;
        }
        while (list.size() > values.length) {
            resize();
        }
        int j = 0;
        for (int i = size;j < list.size();i++) {
            values[i] = list.get(j);
            j++;
            size++;
        }
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound!");
        }
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i] == value || (values[i] != null && values[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound!");
        }
        removedObject = (T) values[index];
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        values[--size] = null;
        resize();
        return removedObject;
    }

    @Override
    public T remove(T value) {
        int index = indexOf(value);
        if (index >= 0) {
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            size--;
            values[size] = null;
            return value;
        }
        throw new NoSuchElementException("There is no element like this!");
    }

    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound!");
        }
        values[index] = value;
    }

    private void resize() {
        if (size >= arraySize) {
            Object[] newValues = new Object[size * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
        if (size >= DEFAULT_SIZE && size < arraySize / 4) {
            Object[] newValues = new Object[size * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }
}
