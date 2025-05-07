package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        growIfArrayFull();
        if (index == size) {
            elementData[size++] = value;
            return;
        }
        int tailLength = size - index;
        System.arraycopy(elementData, index, elementData, index + 1, tailLength);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int minLength = Math.max(list.size() + size, DEFAULT_CAPACITY);
        grow(minLength);
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        elementData[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        T elemToRemove = (T) elementData[index];
        if (index == size) {
            elementData[index] = null;
            return elemToRemove;
        }
        int tailLength = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, tailLength);
        size--;
        return elemToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            grow(elementData.length);
        }
    }

    private void grow(int minLength) {
        int newCapacity = minLength + (minLength >> 1);
        Object[] tmp = new Object[newCapacity];
        System.arraycopy(elementData, 0, tmp, 0, size);
        elementData = tmp;
    }

    private void checkIndex(int index, int upperBound) {
        if (index < 0 || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d out of bounds for size %d", index, size));
        }
    }
}
