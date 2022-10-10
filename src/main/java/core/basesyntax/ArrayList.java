package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;

    private Object[] values;
    private int size;

    public ArrayList() {
        this.values = new Object[START_CAPACITY];
    }

    private Object[] grow() {
        int oldCapacity = values.length;
        Object[] buffer = values;
        values = new Object[oldCapacity + (oldCapacity >> 1)];
        System.arraycopy(buffer, 0, values, 0, size);
        return values;
    }

    @Override
    public void add(T value) {
        if (values.length == size) {
            values = grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBound");
        }

        if (values.length == size) {
            values = grow();
        }
        System.arraycopy(values, index,
                values, index + 1,
                size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            values[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("IndexOutOfBound");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't set");
        }
        T oldValue = (T) values[index];
        values[index] = value;
    }

    private void fastRemove(Object[] helpToRemoveArray, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(helpToRemoveArray, i + 1, helpToRemoveArray, i, newSize - i);
        }
        helpToRemoveArray[size = newSize] = null;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove");
        }
        final Object[] helpToRemoveArray = values;
        T oldValue = (T) helpToRemoveArray[index];
        fastRemove(helpToRemoveArray, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] helpToRemoveArray = values;
        for (int i = 0; i < size; i++) {
            if (element == helpToRemoveArray[i]
                    || element != null && element.equals(helpToRemoveArray[i])) {
                fastRemove(helpToRemoveArray, i);
                return element;
            }
        }

        throw new NoSuchElementException("Not found such element");

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
