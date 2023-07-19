package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (index > size || index < 0) {
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
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        @SuppressWarnings("unchecked") T oldValue = (T) elementData[index];
        fastRemove(elementData, index);

        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(element, elementData[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element not found: " + element);
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

    private void fastRemove(Object[] objects, int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(objects, index + 1, objects, index, newSize - index);
        }

        objects[size = newSize] = null;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index
                    + " for ArrayList size " + size);
        }
    }

}
