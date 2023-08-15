package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkRangeForIndex(index);
        growArray();
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
        checkRangeForIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkRangeForIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRangeForIndex(index);
        final T elementRemove = values[index];
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(values, index + 1, values, index, newSize - index);
        }
        size--;
        return elementRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || (values[i] != null && values[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*private void checkRangeForIndexForAdd(int index) {
        if (index != 0 && (index < 0 || index > size)) {
            throw new ArrayListIndexOutOfBoundsException("The index does not exist");
        }
    }*/

    private void checkRangeForIndex(int index) {
        if (index != 0 && (index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("The index does not exist");
        }
    }

    private void growArray() {
        if (size == values.length) {
            int newLength = values.length + values.length / 2;
            values = Arrays.copyOf(values, newLength);
        }
    }
}
