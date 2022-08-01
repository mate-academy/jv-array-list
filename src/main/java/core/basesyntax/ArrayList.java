package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        increaseArray();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Wrong index out of bounds, please check index!");
        }
        increaseArray();
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
        acceptIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        acceptIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        acceptIndex(index);
        T removed = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, values[i])) {
                System.arraycopy(values, i + 1, values, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Wrong index out of bounds, please check index!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void acceptIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Wrong index out of bounds, please check index!");
        }
    }

    private void increaseArray() {
        if (size == values.length) {
            T[] newArray = (T[]) new Object[(int) (size + size << 1)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }
}
