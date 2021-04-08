package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRSIZE = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_ARRSIZE];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        try {
            if (size == elementData.length) {
                elementData = grow();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
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
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        T oldValue = elementData[index];
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        int numMoved = size - index - 1;
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (elementData[i] == element || element != null && element.equals(elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int newCapacity() {
        return (size * 3) / 2;
    }

    private T[] grow() {
        T[] growArr = (T[]) new Object[newCapacity()];
        System.arraycopy(elementData, 0, growArr, 0, size);
        return growArr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
