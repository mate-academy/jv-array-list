package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The capacity of ArrayList cannot be negative");
        }
        values = new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public void growIfNeeded() {
        if (values.length == size) {
            Object[] newArray = new Object[values.length + (values.length >> 1)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfNeeded();
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Cannot add value on index that is larger than size of list");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add value on negative index");
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    public Object[] convertFromListToArray(List<T> list) {
        Object[] listArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        return listArray;
    }

    public void growIfNeededForAddAll(List<T> list) {
        Object[] listArray = convertFromListToArray(list);
        while ((size + listArray.length) >= values.length) {
            Object[] newArray = new Object[values.length + (values.length >> 1)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listArray = convertFromListToArray(list);
        growIfNeededForAddAll(list);
        System.arraycopy(listArray,0, values, size, listArray.length);
        size += list.size();
    }

    public void checkIndex(int index) {
        if (index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Cannot get value on index that is larger than size of list");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cannot get value on negative index");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        boolean isElement = false;
        T removedElement = null;
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                removedElement = (T) values[i];
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                size--;
                isElement = true;
                break;
            }
        }
        if (!isElement) {
            throw new NoSuchElementException("Not found " + element + " in the list");
        }
        return removedElement;
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
