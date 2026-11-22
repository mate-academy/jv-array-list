package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        this.size = 0;
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void addCapacity() {
        if (size == values.length) {
            values = Arrays.copyOf(values, (int) (size * 1.5));
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
        int oldLength = values.length;
        if (oldLength < size + list.size()) {
            values = Arrays.copyOf(values, oldLength + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            values[size++] = list.get(i);
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                removeByIndex(i);
                break;
            }
            if (i == size - 1) {
                throw new NoSuchElementException(
                        "The element: " + element + "does not exist in the list");
            }
        }
        return element;
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
