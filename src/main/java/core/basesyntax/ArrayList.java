package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            rangeCheck(index);
        }
        if (size == elementData.length) {
            ensureCapacity();
        }
        if (index == size) {
            elementData[size] = value;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > elementData.length) {
            ensureCapacity();
        }
        int indexOfList = 0;
        int forLoop = size;
        for (int i = size; i < forLoop + list.size(); i++) {
            elementData[i] = list.get(indexOfList);
            size++;
            indexOfList++;
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final T removedElement = (T) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                final T removedElement = (T) elementData[i];
                for (int j = i; j < size - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[size - 1] = null;
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of size"
                    + System.lineSeparator()
                    + "Index: " + index + System.lineSeparator()
                    + "Size: " + size);
        }
    }

    private void ensureCapacity() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }
}
