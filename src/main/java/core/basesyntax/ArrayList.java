package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final double INCREASE_COEFF = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int currentSize;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseArrayIfNeed();
        elements[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index : " + index
                    + " out of bonds: [" + 0 + ", " + currentSize + "]");
        } else if (index == currentSize) {
            add(value);
            return;
        }
        increaseArrayIfNeed();
        System.arraycopy(elements, index, elements, index + 1, currentSize - index);
        elements[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexLimits(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexLimits(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexLimits(index);
        T elementToReturn = elements[index];
        System.arraycopy(elements, index + 1, elements, index, currentSize - 1 - index);
        currentSize--;
        return elementToReturn;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(element, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void increaseArrayIfNeed() {
        if (elements.length == currentSize) {
            T[] temp = elements;
            elements = (T[]) new Object[(int)(currentSize * INCREASE_COEFF)];
            System.arraycopy(temp, 0, elements, 0, currentSize);
        }
    }

    private void checkIndexLimits(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index : " + index
                    + " out of bonds: [" + 0 + ", " + currentSize + "]");
        }
    }
}
