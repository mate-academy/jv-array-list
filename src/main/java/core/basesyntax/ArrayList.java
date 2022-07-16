package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private T[] elementData = (T[]) new Object[CAPACITY];
    private int size = 0;

    public void ensureCapacity() {
        if (size == elementData.length) {
            T[] newData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newData, 0, size);
            elementData = newData;
        }
    }

    public void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (index == size) {
            add(value);
            return;
        }

        ensureCapacity();

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            ensureCapacity();
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        validIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        T faked = elementData[index];
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        size--;
        return faked;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("file " + element + " not found");
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
