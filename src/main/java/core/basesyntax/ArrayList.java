package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_ITEMS_NUMBER = 10;

    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_ITEMS_NUMBER];
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
        if (index == size) {
            add(value);
        } else if (index < size && index >= 0) {
            if (size == elementData.length) {
                elementData = grow();
            }
            Object[] newElementData = new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            newElementData[index] = value;
            System.arraycopy(elementData, index, newElementData, index + 1, size - index);
            elementData = newElementData;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index Out of Bounds");
        }

    }

    private Object[] grow() {
        Object[] newElementData = new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newElementData = new Object[size + list.size()];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        elementData = newElementData;
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i), size);
        }
    }

    @Override
    public T get(int index) {
        if (index <= size - 1 && index >= 0) {
            return (T) elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is negative or out of bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            Object[] newElementData = new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index + 1, newElementData, index, size - index - 1);
            T valueToRemove = (T) elementData[index];
            elementData = newElementData;
            size--;
            return valueToRemove;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is negative or out of bounds");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == null && element == null)
                    || (Objects.equals(elementData[i], element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //remove later
    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
