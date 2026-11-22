package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double COEFFICIENT = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        this.size = 0;
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void addCapacity() {
        if (size == values.length) {
            T[] newValues = (T[]) new Object[(int) (values.length * COEFFICIENT)];
            for (int index = 0; index < values.length; index++) {
                newValues[index] = values[index];
            }
            values = newValues;
        }
    }

    private void isIndexException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "intdex: " + index + " - outside the sheet size - " + size);
        }
    }

    private void removeByIndex(int index) {
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
    }

    @Override
    public void add(T value) {
        addCapacity();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            isIndexException(index);
        }
        addCapacity();
        System.arraycopy(values, index, values, index + 1, size++ - index);
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        isIndexException(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexException(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexException(index);
        T element = values[index];
        removeByIndex(index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if ((values[index] == element) || (values[index] != null && values[index].equals(
                    element))) {
                removeByIndex(index);
                return element;
            }
        }
        throw new NoSuchElementException(
                "The element: " + element + "does not exist in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
