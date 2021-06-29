package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private T[] values = (T[]) new Object[INITIAL_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        resizingCheck();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0) {
            indexCheck(index - 1);
        }
        resizingCheck();
        T[] tempoBuffer = (T[]) new Object[size - index];
        System.arraycopy(values, index, tempoBuffer, 0, size - index);
        values[index] = value;
        System.arraycopy(tempoBuffer, 0, values, index + 1, size - index);
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
        indexCheck(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T result = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((values[i] != null && values[i].equals(element))
                    || (values[i] == null && element == null)) {
                remove(i);
                return element;
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

    private void resizingCheck() {
        if (size >= values.length) {
            T[] tempoBuffer = values;
            values = (T[]) new Object[(int)(size * 1.5)];
            System.arraycopy(tempoBuffer, 0, values, 0, size);
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out allowable range");
        }
    }
}
