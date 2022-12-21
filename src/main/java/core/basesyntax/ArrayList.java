package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_FACTOR = 1.5;
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
        if (index == size) {
            add(value);
            return;
        }
        indexCheck(index);
        checkSize();
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkSize();
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        Object element = elementData[index];
        size--;
        if (index < size) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elementData[i])
                    || element == null && elementData[i] == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + "does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + "is invalid for size: " + size;
    }

    private void grow() {
        int newCapacity = (int) (elementData.length * INCREASE_FACTOR);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0,
                newArray, 0, size);
        elementData = newArray;
    }
}
