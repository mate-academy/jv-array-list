package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String ARRAY_LIST_IOOB_EXCEPTION_TEXT
            = "No element with such index";
    private static final int WRONG_INDEX = -1;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_LIST_IOOB_EXCEPTION_TEXT);
        }
        if (size == data.length) {
            grow();
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
        T oldValue = (T) data[index];
        size--;
        System.arraycopy(data, index + 1, data, index, size - index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = WRONG_INDEX;
        for (int i = 0; i < size; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                index = i;
            }
        }
        if (index != WRONG_INDEX) {
            return remove(index);
        }
        throw new NoSuchElementException("No such element: " + element + " in ArrayList");
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
        Object[] temp = data;
        data = new Object[size + (size >> 1)];
        System.arraycopy(temp, 0, data, 0, size);
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(ARRAY_LIST_IOOB_EXCEPTION_TEXT);
        }
    }
}
