package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MULTIPLICATION_INDEX = 1.5d;
    private static final int DEFAULT_CAPACITY = 10;

    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        extractedArray();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not exist in Array list");
        }
        extractedArray();
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
        validateIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementData[index] = value;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not exist in Array list");
        }
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element.");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private void extractedArray() {
        if (size == elementData.length) {
            T[] extractedArray = (T[]) new Object[(int) (elementData.length
                    * MULTIPLICATION_INDEX)];
            System.arraycopy(elementData, 0, extractedArray, 0, size);
            elementData = extractedArray;
        }
    }
}
