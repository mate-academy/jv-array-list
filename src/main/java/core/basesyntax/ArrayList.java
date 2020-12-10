package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity = DEFAULT_CAPACITY;
    private Object[] items = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        size++;
        ensureCapacity();
        items[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        size++;
        if (isValidIndex(index)) {
            ensureCapacity();
            System.arraycopy(items, index, items, index + 1, size - index - 1);
            items[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Can`t add element to non existing index");
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
        if (isValidIndex(index)) {
            return (T) items[index];
        }
        throw new ArrayIndexOutOfBoundsException("Can`t get element, no such index");
    }

    @Override
    public void set(T value, int index) {
        if (isValidIndex(index)) {
            items[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Can`t set element, no such index");
        }
    }

    @Override
    public T remove(int index) {
        if (isValidIndex(index)) {
            final T temp = get(index);
            size--;
            System.arraycopy(items, index + 1, items, index, size - index);
            items[size] = null;
            return temp;
        }
        throw new ArrayIndexOutOfBoundsException("Can`t remove element, no such index");
    }

    @Override
    public T remove(T t) {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (t == null && get(i) == null || get(i).equals(t)) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Can`t find such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (capacity < size + 1) {
            Object[] temp = items;
            capacity = capacity * 3 / 2 + 1;
            items = new Object[capacity];
            System.arraycopy(temp, 0, items, 0, temp.length);
        }
    }

    private boolean isValidIndex(int index) {
        return index < size && index >= 0;
    }

}
