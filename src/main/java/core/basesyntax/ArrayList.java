package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_LENGTH = 10;
    private static final double MULTIPLIER = 1.5;
    private static final int EMPTY_ELEMENT_SIZE = 0;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        checkForArrayIncreasing();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexForException(index);
        checkForArrayIncreasing();
        System.arraycopy(elementData, index,
                elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkForArrayIncreasing();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForException(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForException(index);
        T removeElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element!!!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_ELEMENT_SIZE;
    }

    public void checkForArrayIncreasing() {
        if (size == elementData.length) {
            increase();
        }
    }

    public void increase() {
        int newLength = (int) (elementData.length * MULTIPLIER);
        T[] elementDataNewLength = (T[]) new Object[newLength];
        System.arraycopy(elementData, 0, elementDataNewLength, 0, elementData.length);
        elementData = elementDataNewLength;
    }

    private void checkIndexForException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index:" + index);
        }
    }
}
