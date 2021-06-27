package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] values = new Object[INITIAL_SIZE];
    private int size = 0;

    private void resizingCheck() {
        if (size >= values.length) {
            Object[] tempoBuffer = values;
            values = new Object[(int)(size * 1.5)];
            System.arraycopy(tempoBuffer, 0, values, 0, size);
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out allowable range");
        }
    }

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
        Object[] tempoBuffer = new Object[size - index];
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
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        Object result;
        indexCheck(index);
        result = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return (T) result;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((values[i] != null && values[i].equals(element))
                    || (values[i] == null && element == null)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Required element not found");
        }
        remove(index);
        return element;
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
