package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWING_COEFFICIENT = 1.5;
    private static final int NON_EXISTENT_INDEX = -1;
    private Object[] values;
    private int lengthCounter;
    private int currentCapacity;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        grow(currentCapacity);
        values[lengthCounter] = value;
        lengthCounter++;
    }

    @Override
    public void add(T value, int index) {
        if (index > lengthCounter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        grow(currentCapacity);
        System.arraycopy(values, index, values, index + 1, lengthCounter - index);
        values[index] = value;
        lengthCounter++;
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
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T value = (T) values[index];
        int newLengthCounter = lengthCounter - 1;
        if (newLengthCounter >= index) {
            System.arraycopy(values, index + 1, values, index, newLengthCounter - index);
        }
        values[newLengthCounter] = null;
        lengthCounter--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = NON_EXISTENT_INDEX;
        if (element == null) {
            lengthCounter--;
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], element)) {
                index = i;
            }
        }
        checkElement(element, index);
        checkIndex(index);
        T value = (T) values[index];
        remove(index);
        return value;
    }

    @Override
    public int size() {
        return lengthCounter;
    }

    @Override
    public boolean isEmpty() {
        if (lengthCounter == 0) {
            return true;
        }
        return false;
    }

    public void grow(int oldCapacity) {
        if (lengthCounter == currentCapacity) {
            int newCapacity = (int) (oldCapacity * GROWING_COEFFICIENT);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
            currentCapacity = values.length;
        }
    }

    public void checkIndex(int index) {
        if (index >= lengthCounter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    public void checkElement(T element, int index) {
        if (index < 0) {
            throw new NoSuchElementException("The element " + element + " is not exist");
        }
    }
}
