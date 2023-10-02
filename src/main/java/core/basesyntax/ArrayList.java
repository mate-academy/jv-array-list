package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_SIZE_GROW = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void growSize() {
        if (values.length == size) {
            int oldCapacity = values.length;
            int newCapacity = (int) (oldCapacity * DEFAULT_SIZE_GROW);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect: " + index);
        }
    }

    private void checkIndexEqualsSize(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect: " + index);
        }
    }

    @Override
    public void add(T value) {
        growSize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        growSize();
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
        checkIndexEqualsSize(index);
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexEqualsSize(index);
        checkIndex(index);
        growSize();
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexEqualsSize(index);
        checkIndex(index);
        T removedElement = values[index];
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || values[i] != null
                    && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element);
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
