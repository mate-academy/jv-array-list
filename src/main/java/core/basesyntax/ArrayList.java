package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    public void add(T value) {
        grow();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        addException(value, index);
        grow();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        getException(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        setException(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        removeException(index);
        T removedElement = (T) values[index];
        System.arraycopy(values, index + 1, values, index, --size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        return remove(findElement(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int findElement(T element) {
        int remElement = -1;
        for (int i = 0; i < size; i++) {
            if (values[i] == element
                    || values[i] != null && values[i].equals(element)) {
                remElement = i;
            }
        }
        if (remElement < 0) {
            throw new NoSuchElementException("Cannot find element: " + element);
        }
        return remElement;
    }

    private void grow() {
        if (values.length <= size) {
            Object[] newElementsData = new Object[values.length + (values.length >> 1)];
            System.arraycopy(values, 0, newElementsData, 0, values.length);
            values = newElementsData;
        }
    }

    private void addException(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add value "
                    + value + " with index "
                    + index);
        }
    }

    private void getException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot give value from index: " + index);
        }
    }

    private void setException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot set index: " + index);
        }
    }

    private void removeException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element by index: "
                    + index);
        }
    }
}
