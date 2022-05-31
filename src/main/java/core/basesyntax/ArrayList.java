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
        if (size == values.length) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            grow();
        }
        if (index != size) {
            validindex(index);
            System.arraycopy(values, index,
                    values, index + 1,
                    size - index);
        }
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
        validindex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        validindex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        validindex(index);
        Object[] oldValues = values;
        T oldValue = (T) oldValues[index];
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(oldValues, index + 1, oldValues, index, newSize - index);
        }
        oldValues[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((values[i] != null && values[i].equals(element))
                    || values[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
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
        if (values.length > 0) {
            Object[] newelementData = new Object[values.length + (values.length >> 1)];
            System.arraycopy(values, 0, newelementData, 0, values.length);
            values = newelementData;
        }
    }

    private void validindex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid " + index);
        }
    }
}
