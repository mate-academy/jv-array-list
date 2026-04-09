package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NUM = 10;
    private int size;
    private Object[] data;

    public ArrayList() {
        data = new Object[NUM];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("\n"
                    + "\"Index \" + index + \" out of bounds for size \" + size");
        }
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T oldIndex = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        data[size] = null;
        return oldIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || (element != null && element.equals(data[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        Object[] newData = new Object[(int) (data.length + data.length / 2)];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("\n"
                    + "\"Index \" + index + \" out of bounds for size \" + size");
        }
    }

    private void ensureCapacity() {
        if (size == data.length) {
            grow();
        }
    }
}
