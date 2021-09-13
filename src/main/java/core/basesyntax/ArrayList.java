package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        extendArray();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        extendArray();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] objects = new Object[list.size()];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = list.get(i);
        }
        if (objects.length > elementData.length - size) {
            int newCapacity = objects.length + size;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        System.arraycopy((T[]) objects, 0, elementData, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        throwException(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        throwException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        throwException(index);
        int numMoved = size - index - 1;
        T element = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                int numMoved = size - i - 1;
                T oldElement = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, numMoved);
                elementData[--size] = null;
                return oldElement;
            }
        }
        throw new NoSuchElementException("Element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendArray() {
        if (size == elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void throwException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
