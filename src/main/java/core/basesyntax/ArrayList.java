package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] objects = convertListInArray(list);
        resizeIfNeeded(size + objects.length);
        System.arraycopy(objects, 0, elements, size, objects.length);
        size = size + objects.length;
    }

    @Override
    public T get(int index) {
        checkGetSetRemoveIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkGetSetRemoveIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkGetSetRemoveIndex(index);
        T removeElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        return remove(getIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded() {
        resizeIfNeeded((int) (size * GROW_COEFFICIENT));
    }

    private void resizeIfNeeded(int arrayLength) {
        if (elements.length < arrayLength) {
            Object[] newArray = new Object[arrayLength];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is not valid " + index);
        }
    }

    public void checkGetSetRemoveIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index is not valid  " + index);
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (objectEquals(element, (T) elements[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private boolean objectEquals(T value1, T value2) {
        if (value1 == value2) {
            return true;
        }
        return value1 != null && value1.equals(value2);
    }

    private Object[] convertListInArray(List<T> list) {
        Object[] arrayValues = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayValues[i] = list.get(i);
        }
        return arrayValues;
    }
}
