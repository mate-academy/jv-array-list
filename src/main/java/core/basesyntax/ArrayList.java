package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final double DEFAULT_SIZE_GROW = 1.5;

    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        checkSize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        checkSize();

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
        final T removeValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - (index + 1));
        size--;
        return removeValue;

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("informative message");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size < values.length) {
            return;
        }
        int oldCapacity = values.length;
        int newCapacity = (int) (oldCapacity * DEFAULT_SIZE_GROW);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, size);
        values = newValues;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }

    }
}
