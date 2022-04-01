package core.basesyntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_MAX_LENGTH = 10;
    private Object[] arrayOfData;
    private int size;

    public ArrayList() {
        arrayOfData = new Object[DEFAULT_MAX_LENGTH];
    }

    public ArrayList(int length) {
        arrayOfData = new Object[length];
    }

    public ArrayList(Collection<T> list) {
        arrayOfData = list.toArray();
    }

    @Override
    public void add(T value) {
        add(value, arrayOfData, size);
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
            add(value, arrayOfData, index);
        } else {
            if (size == arrayOfData.length) {
                arrayOfData = grow();
            }
            System.arraycopy(arrayOfData, index, arrayOfData, index + 1, size - index);
            arrayOfData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= arrayOfData.length) {
            arrayOfData = grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
        //size += list.size();
    }

    public Object[] grow() {
        return grow(size + 1);
    }

    public Object[] grow(int minLength) {
        int newLength = arrayOfData.length + arrayOfData.length >> 1;
        if (newLength > minLength) {
            return arrayOfData = Arrays.copyOf(arrayOfData, newLength);
        } else {
            return arrayOfData = Arrays.copyOf(arrayOfData, minLength);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        return (T) arrayOfData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        arrayOfData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        final T oldValue = (T) arrayOfData[index];
        System.arraycopy(arrayOfData, index + 1, arrayOfData, index, size - index - 1);
        size = size - 1;
        arrayOfData[size] = null;
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
            if (Objects.equals(arrayOfData[i], element)) {
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
