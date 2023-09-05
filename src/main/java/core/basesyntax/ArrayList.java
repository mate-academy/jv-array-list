package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private T[] values = (T[]) new Object[INITIAL_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0) {
            checkIndex(index - 1);
        }
        resize();
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
        final T result = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((values[i] != null && values[i].equals(element)) || (values[i] == element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Required element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size >= values.length) {
            T[] tempoBuffer = values;
            values = (T[]) new Object[(int)(size * 1.5)];
            System.arraycopy(tempoBuffer, 0, values, 0, size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of allowed range");
        }
    }
}
