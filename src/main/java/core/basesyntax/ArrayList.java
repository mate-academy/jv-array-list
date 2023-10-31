package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private int maxListSize = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[maxListSize];
    }

    private void growIfArrayFull() {
        if (size == maxListSize) {
            maxListSize = (int) (maxListSize + maxListSize * 0.5);
            T[] tempValues = values;
            values = (T[]) new Object[maxListSize];
            System.arraycopy(tempValues, 0, values, 0, size);
        }
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size] = value;
        size++;
        growIfArrayFull();
    }

    @Override
    public void add(T value, int index) {
        size++;
        throwNewIndexOutOfBoundsException(index);
        growIfArrayFull();
        for (int currentIndex = size - 2; currentIndex > index; currentIndex--) {
            T tempCurrentValueHolder = values[currentIndex];
            T tempPreviousValueHolder = values[currentIndex - 1];
            values[currentIndex + 1] = tempCurrentValueHolder;
            values[currentIndex] = tempPreviousValueHolder;
        }
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        T[] tempValues = (T[]) new Object[list.size()];
        for (int i = 0; i < tempValues.length; i++) {
            tempValues[i] = list.get(i);
            add(tempValues[i]);
        }
    }

    @Override
    public T get(int index) {
        throwNewIndexOutOfBoundsException(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        throwNewIndexOutOfBoundsException(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        throwNewIndexOutOfBoundsException(index);
        T deletedValue = values[index];
        int y = index + 1;
        while (y < size) {
            values[index++] = values[y++];
        }
        values[y] = null;
        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || (element != null && element.equals(values[i]))) {
                int y = i + 1;
                while (y < size) {
                    values[i++] = values[y++];
                }
                values[y] = null;
                size--;
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void throwNewIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds!");
        }
    }
}
