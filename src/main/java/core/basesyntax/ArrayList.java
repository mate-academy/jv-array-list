package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            increaseCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        if (elementData.length == index) {
            increaseCapacity();
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
        checkIndex(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T deletedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || element.equals(elementData[i])
                    && element != null) {
                remove(i);
                return element;
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

    private void increaseCapacity() {
        T[] biggerArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(elementData, 0, biggerArray, 0, size);
        elementData = biggerArray;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
