package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_COEFFICIENT = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        growIfFullsSize();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexInBoundAdd(index);
        growIfFullsSize();
        Object[] data = this.data;
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        isIndexInBoundGet(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInBoundGet(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInBoundGet(index);
        final Object[] es = data;
        T oldValue = (T) es[index];
        remove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] returnedValues = data;
        for (int i = 0; i < size; i++) {
            if (isElementFound(element, i)) {
                T oldValue = (T) returnedValues[i];
                remove(returnedValues, i);
                return oldValue;
            }
        }
        throw new NoSuchElementException();
    }

    private void remove(Object[] returnValues, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(returnValues, i + 1, returnValues, i, newSize - i);
        }
        returnValues[size = newSize] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFullsSize() {
        if (size == data.length) {
            data = (T[]) growIfFull();
        }
    }

    private Object[] grow(int minCapacity) {
        int newCapacity = (int) Math.round((minCapacity + 1) * GROW_COEFFICIENT);
        Object[] grown = new Object[newCapacity];
        System.arraycopy(data, 0, grown, 0, data.length);
        return grown;
    }

    private Object[] growIfFull() {
        return grow(size + 1);
    }

    private void isIndexInBoundGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }

    private void isIndexInBoundAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }

    private boolean isElementFound(T element, int i) {
        return (data[i] == null && element == null) || (data[i] != null && data[i].equals(element));
    }

}
