package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String NO_SUCH_ELEMENT = "No such element present ";
    private static final String INVALID_INDEX = "Invalid index ";
    private T[] values;
    private int size;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX + index);
        }
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
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T currentValue = get(i);
            if (currentValue == element || currentValue != null && currentValue.equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            int newSize = values.length + (values.length >> 1);
            T[] largerCapacity = (T[]) new Object[newSize];
            System.arraycopy(values, 0, largerCapacity,0, size);
            values = largerCapacity;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INVALID_INDEX + index);
        }
    }
}
