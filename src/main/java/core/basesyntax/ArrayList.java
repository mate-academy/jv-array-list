package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkNewElementIndex(index);
        growIfArrayFull();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        checkExistingElementIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkExistingElementIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkExistingElementIndex(index);
        T value = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (compareElements(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Couldn't find such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkNewElementIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Trying to operate"
                    + " with invalid index: " + index);
        }
    }

    private void checkExistingElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Trying to operate"
                    + " with invalid index: " + index);
        }
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            int newLength = (int)(values.length * CAPACITY_MULTIPLIER);
            T[] newValues = (T[]) new Object[newLength];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private Boolean compareElements(T firstElement, T secondElement) {
        return firstElement == null ? secondElement == null : firstElement.equals(secondElement);
    }
}
