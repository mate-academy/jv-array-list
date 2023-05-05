package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWING_COEFFICIENT = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        verifyIndexIsValid(index);
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        set(value, index);
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
        verifyIndexIsValid(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndexIsValid(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndexIsValid(index);
        T object = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return object;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(element, elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element present: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean elementsAreEqual(T first, T second) {
        return (first == second) || (second != null && second.equals(first));
    }

    private boolean verifyIndexIsValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Invalid index passed: %d for size: %d", index, size));
        }
        return true;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newSize = (int) (elementData.length * GROWING_COEFFICIENT);
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }
}
