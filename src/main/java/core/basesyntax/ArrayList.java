package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void arrayCopy() {
        T[] valuesCopy = (T[]) new Object[(int) (values.length * INCREASE_RATE)];
        System.arraycopy(values,0,valuesCopy, 0, values.length);
        values = valuesCopy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            arrayCopy();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }

        if (size == values.length) {
            arrayCopy();
        }

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
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i <= size; i++) {
            if (Objects.equals(values[i], element)) {
                size--;
                System.arraycopy(values, i + 1, values, i, size - i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such value in the array");
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
