package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size + 1 > data.length) {
            increaseSize();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can`t add element to: "
                    + index + " index");
        }
        if (size + 1 > data.length) {
            increaseSize();
        }
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
        validIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        Object value = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return (T) value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || (element != null && element.equals(data[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element: " + element + " in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can`t set to " + index + " index");
        }
    }

    private void increaseSize() {
        Object[] temporallyArray = new Object[((int) (data.length * 1.5))];
        System.arraycopy(data, 0, temporallyArray, 0, size);
        data = temporallyArray;
    }
}
