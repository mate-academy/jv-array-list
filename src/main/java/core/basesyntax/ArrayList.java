package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final float CAPACITY_MULTIPLIER = 1.5f;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index is not valid");
        }
        size++;
        if (size == values.length) {
            grow();
        }
        if (size - (index + 1) >= 0) {
            System.arraycopy(values,
                    index,
                    values,
                    index + 1,
                    size - (index + 1));
        }
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexAvailability(index);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        checkIndexAvailability(index);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                values[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        checkIndexAvailability(index);
        T deleted = values[index];
        T[] temp = values;
        if (size - (index + 1) >= 0) {
            System.arraycopy(temp,
                    index + 1,
                    values,
                    index + 1 - 1,
                    size - (index + 1));
        }
        size--;
        return deleted;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((values[i] == element)
                    || values[i] != null
                    && values[i].equals(element)) {
                return remove(i);
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

    private void grow() {
        T[] oldElementData = values;
        values = (T[]) new Object[(int)(values.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(oldElementData,
                0,
                values,
                0,
                oldElementData.length);
    }

    private void checkIndexAvailability(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Given index is not valid for size");
        }
    }
}
