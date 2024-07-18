package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void checkCapacity() {
        if (elementData.length <= size) {
            int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elementData [size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkCapacity();
        for (int i = size - 1; i >= index; i--) {
            elementData[i++] = elementData[i];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > elementData.length) {
            checkCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData [size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
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
        final T removedElement = (T) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData [size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                T removedElement = (T) elementData[i];
                remove(i);
                return removedElement;
            }
        }
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

}
