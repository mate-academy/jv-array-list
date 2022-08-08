package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increase();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (index == size) {
            add(value);
        } else {
            increase();
            Object[] valuesTemp = new Object[size - index + 1];
            System.arraycopy(values, index, valuesTemp, 0, valuesTemp.length);
            values[index] = value;
            System.arraycopy(valuesTemp, 0, values, index + 1, valuesTemp.length - 1);
            size++;
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
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object oldValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("There is no such element " + element);
        }
        System.arraycopy(values, index + 1, values,
                index, size - index - 1);
        size--;
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

    private void increase() {
        if (size >= values.length) {
            Object[] valuesTemp = new Object[size * 3 / 2];
            System.arraycopy(values, 0, valuesTemp, 0, size);
            values = valuesTemp;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Too big index " + index);
        }
    }

    private void checkAddIndex(int index) {
        if (index != 0 && (index > size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Too big index " + index);
        }
    }
}
