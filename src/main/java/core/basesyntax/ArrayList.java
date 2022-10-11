package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    private void checkIndex(int index) {
        if (size != 0 && (index >= size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    public void capacityIncrease() {
        Object[] newArray = new Object[size + size / 2];
        System.arraycopy(elements,0, newArray, 0,elements.length);
        elements = newArray;
    }

    @Override
    public void add(T value) {
        if (size >= DEFAULT_CAPACITY) {
            capacityIncrease();
        }

        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }

        if (size >= DEFAULT_CAPACITY) {
            capacityIncrease();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("There is no such element"
                + "[" + element + "] in ArrayList");
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
