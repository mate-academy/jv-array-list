package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MEMORY_INCREASE_MULTIPLIER = 1.5;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkForNecessityOfIncreasingArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForNecessityOfIncreasingArray();
        if (index != size) {
            validateIndex(index);
            System.arraycopy(values, index, values, index + 1, size - index);
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkForNecessityOfIncreasingArray();
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T oldValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element don't present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] newArray = new Object[(int) (values.length * MEMORY_INCREASE_MULTIPLIER)];
        System.arraycopy(values, 0, newArray, 0, size);
        return newArray;
    }

    private void checkForNecessityOfIncreasingArray() {
        if (size == values.length) {
            values = grow();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
