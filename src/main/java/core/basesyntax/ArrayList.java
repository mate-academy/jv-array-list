package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIALIZATION_SIZE = 10;
    private static final double ARRAY_RATIO = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[INITIALIZATION_SIZE];
    }

    @Override
    public void add(T value) {
        expandCapacity();
        values[size] = value;
        size++;
    }

    public void add(T value, int index) {
        checkIndexForAdd(index);
        T[] newValues = Arrays.copyOf(values, values.length + 1);
        System.arraycopy(values, index, newValues, index + 1, size + 1);
        newValues[index] = value;
        values = newValues;
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

    public T remove(int index) {
        checkIndex(index);
        T removedElement = values[index];
        System.arraycopy(values, index + 1, values, index,size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandCapacity() {
        if (values.length == size) {
            int newCapacity = (int) (size * ARRAY_RATIO);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }
}
