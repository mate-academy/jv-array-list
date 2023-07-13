package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There are no values for this index");
        }
        growIfArrayFull();
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        T[] newValues = (T[]) new Object[size + listSize];
        if (listSize > (values.length - size)) {
            values = grow(size + listSize);
        }
        System.arraycopy(values, 0, newValues, 0, size);
        for (int i = size, k = 0; i < newValues.length; i++, k++) {
            add(list.get(k));
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
        final T removedElement = get(index);
        T[] newValues = (T[]) new Object[size];
        System.arraycopy(values, 0, newValues, 0, index);
        if (newValues.length - (index + 1) >= 0) {
            System.arraycopy(values, index + 1, newValues,
                    index, newValues.length - (index + 1));
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

    private T[] grow(int minCapacity) {
        int oldCapacity = values.length;
        if (oldCapacity > 0 || values != null) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            return newValues;
        }
        return null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There are no values for this index");
        }
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            values = grow(size + 1);
        }
    }
}
