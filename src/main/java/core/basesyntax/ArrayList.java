package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = expand(elementData);
        }

        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0 ) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }

        if (size == elementData.length) {
            elementData = expand(elementData);
        }

        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0 ) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }

        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0 ) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }

        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
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

    private Object[] expand(Object[] elementData) {
        Object[] newStorageKey = new Object[elementData.length + (elementData.length >> 1)];

        System.arraycopy(elementData, 0, newStorageKey, 0, elementData.length);

        return newStorageKey;
    }

}
