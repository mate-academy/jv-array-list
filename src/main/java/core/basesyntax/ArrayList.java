package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int defaultCapacity;
    private T[] values;
    private int size;

    public ArrayList() {
        defaultCapacity = 10;
        this.values = (T[]) new Object[defaultCapacity];
    }

    @Override
    public void add(T value) {
        grow();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {

        checkIfIndexOutOfBoundsException(index);
        grow();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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

    public void grow() {
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
