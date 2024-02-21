package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double COEFFICIENT_OF_EXPANSION = 1.5;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {

        growIfArrayFull();

        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }

        growIfArrayFull();

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
        indexValidation(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T removedElement = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? values[i] == null : element.equals(values[i])) {
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

    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            int newCapacity = (int) (size * COEFFICIENT_OF_EXPANSION);
            T[] temp = values;
            values = (T[]) new Object[newCapacity];
            System.arraycopy(temp, 0, values, 0, size);
        }
    }
}
