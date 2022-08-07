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
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add element. "
                    + "Index out of bounds of this list.");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Cannot get element. "
                    + "There is no element at that index.");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Cannot set element. "
                    + "There is no element at that index.");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element. "
                    + "There is no element at that index.");
        }
        Object removedObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        Object removedObject = null;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                removedObject = elementData[i];
                remove(i);
                return (T) removedObject;
            }
        }
        throw new NoSuchElementException("Cannot remove element. "
                + "There is no such element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] grow = new Object[elementData.length + elementData.length / 2];
        for (int i = 0; i < elementData.length; i++) {
            grow[i] = elementData[i];
        }
        return grow;
    }
}
