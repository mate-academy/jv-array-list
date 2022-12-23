package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private T[] buffer;
    private int size;
    private int capacity;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    private void buffer(int length, int srcPos) {
        buffer = (T[]) new Object[length];
        System.arraycopy(values, srcPos, buffer, 0, length);
    }

    private void resize() {
        buffer(size, 0);
        values = (T[]) new Object[capacity + capacity / 2];
        System.arraycopy(buffer, 0, values, 0, buffer.length);
        capacity = values.length;
    }

    private void indexCheck(int index, int rangeMax) {
        if (index < 0 || index > rangeMax) {
            throw new ArrayListIndexOutOfBoundsException("The passed index is wrong");
        }
    }

    public int getIndexOfEqualValue(T element) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            resize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index, size);
        if (size == capacity) {
            resize();
        }
        buffer(size - index, index);
        values[index] = value;
        System.arraycopy(buffer, 0, values, index + 1, buffer.length);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > capacity) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            values[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index, size - 1);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index, size - 1);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index, size - 1);
        buffer(capacity - index - 1, index + 1);
        T element = get(index);
        System.arraycopy(buffer, 0, values, index, buffer.length);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = getIndexOfEqualValue(element);
        if (index == -1) {
            throw new NoSuchElementException();
        } else {
            buffer(capacity - index - 1, index + 1);
            System.arraycopy(buffer, 0, values, index, buffer.length);
            size--;
            return element;
        }
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
