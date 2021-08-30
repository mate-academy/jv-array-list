package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        checkSize();
        System.arraycopy(elementData,
                index,
                elementData,
                index + 1,
                size - index);
        elementData[index] = value;
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
        checkIfIndexIsValid(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexIsValid(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexIsValid(index, size);
        T element = (T) elementData[index];
        System.arraycopy(elementData,
                index + 1,
                elementData,
                index,
                size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There's no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIfIndexIsValid(int index, int length) {
        if (index >= length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    private void checkSize() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        Object[] newElementData = (T[]) new Object[(size >> 1) + size];
        System.arraycopy(elementData,
                0,
                newElementData,
                0,
                elementData.length);
        elementData = (T[]) newElementData;
    }
}
