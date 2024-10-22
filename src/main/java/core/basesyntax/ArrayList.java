package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;

    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[INITIAL_CAPACITY];
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
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for list with size " + size);
        }
        if (size == values.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
        size++;
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
        checkOutOfBounds(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBounds(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBounds(index);
        T el = (T) values[index];
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        size--;
        return el;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element found in list: " + element);
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
        Object[] newValues = new Object[size + size / 2];
        for (int i = 0; i < size; i++) {
            newValues[i] = values[i];
        }
        values = newValues;
    }

    private void checkOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for list with size " + size);
        }
    }
}
