package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_BY = 1.5;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        isCorrectIndex(index);
        if (isFull()) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isCorrectIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (isCorrectIndex(index)) {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        isCorrectIndex(index);
        T oldValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((element == elementData[i])
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    public void grow() {
        int newCapacity = (int)(elementData.length * GROW_BY);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == elementData.length;
    }

    public boolean isCorrectIndex(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
    }
}
