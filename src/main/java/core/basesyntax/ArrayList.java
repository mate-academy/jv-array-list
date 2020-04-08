package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int defaultCapacity = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[defaultCapacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        grow();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {

        checkIfIndexOutOfBoundsException(index);
        if (size + 1 >= values.length) {
            grow();
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

        checkIfIndexOutOfBoundsException(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexOutOfBoundsException(index);
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIfIndexOutOfBoundsException(index);
        T oldValue = get(index);
        decrease(index);
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (values[i] == t || t != null && t.equals(values[i])) {
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
        if (size == values.length) {
            T[] temp = values;
            values = (T[]) new Object[(size * 3) / 2];
            System.arraycopy(temp, 0, values, 0, temp.length);
        }
    }

    private void checkIfIndexOutOfBoundsException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index :" + index + ", size : " + size);
        }
    }

    private void decrease(int index) {
        int numbersMoved = size - index - 1;
        if (numbersMoved > 0) {
            System.arraycopy(values, index + 1, values, index, numbersMoved);
        }
        values[--size] = null;
    }
}
