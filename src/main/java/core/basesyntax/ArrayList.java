package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIALIZATION_SIZE = 10;
    private static final double ARRAY_RATIO = 1.5;
    private T[] values;
    private int currentSize;
    private int lastElement = 0;

    public ArrayList() {
        currentSize = INITIALIZATION_SIZE;
        values = (T[]) new Object[currentSize];
    }

    @Override
    public void add(T value) {
        if (lastElement >= currentSize) {
            grow();
        }
        values[lastElement] = value;
        lastElement++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > lastElement) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        T[] newValues = (T[]) new Object[values.length + 1];
        System.arraycopy(values, 0, newValues, 0, index);
        newValues[index] = value;
        System.arraycopy(values, index, newValues, index + 1, values.length - index);
        values = newValues;
        lastElement++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > currentSize - lastElement) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            values[lastElement] = list.get(i);
            lastElement++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < lastElement) {
            return values[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index is invalid");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < lastElement) {
            values[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is invalid");
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < lastElement) {
            T[] newValues = (T[]) new Object[values.length - 1];
            System.arraycopy(values, 0, newValues, 0, index);
            System.arraycopy(values, index + 1, newValues, index, values.length - index - 1);
            T removedElement = values[index];
            values = newValues;
            lastElement--;
            return removedElement;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is invalid");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                T[] newValues = (T[]) new Object[values.length - 1];
                System.arraycopy(values, 0, newValues, 0, i);
                System.arraycopy(values, i + 1, newValues, i, values.length - i - 1);
                T removedElement = values[i];
                values = newValues;
                lastElement--;
                return removedElement;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return lastElement;
    }

    @Override
    public boolean isEmpty() {
        return lastElement == 0;
    }

    public void grow() {
        currentSize *= ARRAY_RATIO;
        System.arraycopy(values, 0,
                values = (T[]) new Object[currentSize], 0, lastElement);
    }
}
