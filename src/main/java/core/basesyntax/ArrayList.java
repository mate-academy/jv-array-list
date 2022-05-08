package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_LENGTH = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_LENGTH];
    }

    private void resize() {
        if (size == values.length) {
            values = Arrays.copyOf(values, size * 3 / 2);
        }
    }

    private void copyAndRemove(int index) {
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            resize();
        }
        if (index <= size && index >= 0) {
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Bad index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("bad index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("bad index");
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            T objectRemoved = values[index];
            copyAndRemove(index);
            return objectRemoved;
        } else {
            throw new ArrayListIndexOutOfBoundsException("bad index");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null
                    && values[i].equals(element)) {
                copyAndRemove(i);
                return element;
            }
        }
        throw new NoSuchElementException("bad index");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0;
    }
}
