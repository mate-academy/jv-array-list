package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
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
        if (!isIndexValid(index) && index != size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        addElementInMiddle(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexValid(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removedElement = get(index);
        arrayShift(index);
        return removedElement;
    }

    @Override
    public T remove(T t) {
        int index = getIndex(t);
        if (!isIndexValid(index)) {
            throw new NoSuchElementException();
        }
        remove(index);
        return t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        return Arrays.copyOf(elementData, (int) (elementData.length * 1.5));
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }

    private int getIndex(T value) {
        for (int i = 0; i < size; i++) {
            if (value == elementData[i] || value != null && value.equals(elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    private void addElementInMiddle(T value, int index) {
        if (size == elementData.length) {
            elementData = grow();
        }
        if (index == size) {
            add(value);
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    private void arrayShift(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
    }
}
