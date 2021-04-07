package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

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
        if (isIndexExist(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIndexExist(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexExist(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(element, elementData[i])) {
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

    private boolean isIndexExist(int index) {
        return index < 0 || index >= size;
    }

    private T[] grow() {
        T[] biggerArray = (T[]) new Object[newCapacity()];
        System.arraycopy(elementData, 0, biggerArray, 0, size);
        return biggerArray;
    }

    private int newCapacity() {
        return size + (size >> 1);
    }
}
