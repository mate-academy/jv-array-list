package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementsOfData;

    public ArrayList() {
        elementsOfData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        elementsOfData = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == elementsOfData.length) elementsOfData = grow();
        elementsOfData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Bro, it`s a damn bad index: " + index);
        }
        if (size == elementsOfData.length) elementsOfData = grow();
        System.arraycopy(elementsOfData, index, elementsOfData, index + 1, size - index);
        elementsOfData[index] = value;
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
        checkIndex(index);
        return elementsOfData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsOfData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elementsOfData[index];
        System.arraycopy(elementsOfData, index + 1, elementsOfData, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementsOfData[i])) return remove(i);
        }
        throw new NoSuchElementException("no such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] grow() {
        T[] newData = (T[]) new Object[size + (size / 2)];
        System.arraycopy(elementsOfData, 0, newData, 0, size);
        return newData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Bro, it`s a damn bad index: " + index);
        }
    }
}
