package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        increaseSize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        increaseSize();
        checkIndexSize(index);
        System.arraycopy(values, index, values, index + 1, size - index);
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
        checkIndexSize(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexSize(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexSize(index);
        final T removed = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element cannot be found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexSize(int index) {
        if (index >= size || index < 0 || index >= values.length) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of array's size.");
        }
    }

    private void increaseSize() {
        if (values.length == size) {
            Object[] increasedArray = new Object[(int) (size * 1.5)];
            System.arraycopy(values, 0, increasedArray, 0, values.length);
            values = (T[]) increasedArray;
        }
    }
}
