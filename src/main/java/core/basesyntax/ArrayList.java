package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final Object[] EMPTY_SIZE = {};
    private int size = 0;
    private Object[] values = new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {
        if (size == values.length) {
            values = grow(size + 1);
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There are no values for this index");
        }
        if (size == values.length) {
            values = grow(size + 1);
        }
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int numNew = list.size();
        Object[] newValues = new Object[size + numNew];
        if (numNew > (values.length - size)) {
            values = grow(size + numNew);
        }
        System.arraycopy(values, 0, newValues, 0, size);
        for (int i = size, k = 0; i < newValues.length; i++, k++) {
            newValues[i] = list.get(k);
        }
        values = newValues;
        size += numNew;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There are no values for this index");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There are no values for this index");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        final T removedElement = get(index);
        Object[] newValues = new Object[size];
        if (index >= 0) {
            System.arraycopy(values, 0, newValues, 0, index);
        }
        if (newValues.length - (index + 1) >= 0) {
            System.arraycopy(values, index + 1, newValues,
                    index + 1 - 1, newValues.length - (index + 1));
        }
        values = newValues;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if ((element == values[i]) || (element != null && element.equals(values[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There are no such value");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = values.length;
        if (oldCapacity > 0 || values != EMPTY_SIZE) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            return Arrays.copyOf(values, newCapacity);
        } else {
            return new Object[Math.max(DEFAULT_SIZE, minCapacity)];
        }
    }
}
