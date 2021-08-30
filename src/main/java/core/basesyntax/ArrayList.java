package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentIndex = 0;
    private T[] values;

    @SuppressWarnings("unchecked")
    ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growCheck();
        values[currentIndex] = value;
        currentIndex++;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentIndex || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
        growCheck();
        for (int i = currentIndex; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = value;
        currentIndex++;
    }

    private void growCheck() {
        if (currentIndex >= values.length) {
            grow();
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = values.length + (values.length >> 1);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        outOfBoundsCheck(index);
        return values[index];
    }

    private void outOfBoundsCheck(int index) {
        if (index >= currentIndex || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsCheck(index);
        T returnValue = values[index];
        for (int i = index; i < currentIndex - 1; i++) {
            values[i] = values[i + 1];
        }
        currentIndex--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null ? values[i] == element : values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }
}
