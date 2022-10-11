package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFOULT_SIZE = 10;
    private int size;
    private Object[] values;

    public ArrayList() {
        values = new Object[DEFOULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (values.length == size) {
            resize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        if (++size == values.length) {
            resize();
        }

        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        T remoteElement = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        size--;
        return remoteElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                T remotedElement = (T) values[i];
                remove(i);
                return remotedElement;
            }
        }
        throw new NoSuchElementException("Element " + element + " absent in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resize() {
        T[] data = (T[]) values;
        values = new Object[size + (size >> 1)];
        System.arraycopy(data, 0, values, 0, size);
    }

    public void checkIndex(int index) {
        if ((size != 0 || index != size) && (index >= size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }
}
