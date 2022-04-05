package core.basesyntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int MAX_LENGTH = 10;
    private Object[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = new Object[MAX_LENGTH];
    }

    public ArrayList(int length) {
        dataArray = new Object[length];
    }

    public ArrayList(Collection<T> list) {
        dataArray = list.toArray();
    }

    @Override
    public void add(T value) {
        add(value, dataArray, size);
    }

    public void add(T value, Object[] array, int index) {
        if (size == array.length) {
            array = grow();
        }
        array[index] = value;
        size = index + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (index == size) {
            add(value, dataArray, index);
        } else {
            if (size == dataArray.length) {
                dataArray = grow();
            }
            System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
            dataArray[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= dataArray.length) {
            dataArray = grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public Object[] grow() {
        return grow(size + 1);
    }

    public Object[] grow(int minLength) {
        int newLength = dataArray.length + dataArray.length >> 1;
        if (newLength > minLength) {
            return dataArray = Arrays.copyOf(dataArray, newLength);
        } else {
            return dataArray = Arrays.copyOf(dataArray, minLength);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        final T oldValue = (T) dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index - 1);
        size = size - 1;
        dataArray[size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = findIndexByElement(element);
        if (index == -1) {
            throw new NoSuchElementException("Invalid element");
        } else {
            return remove(index);
        }
    }

    public int findIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(dataArray[i], element)) {
                return i;
            }
        }
        return -1;
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
