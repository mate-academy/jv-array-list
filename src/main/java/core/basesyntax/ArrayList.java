package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] items;

    public ArrayList() {
        items = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index <= size && index >= 0) {
            ensureCapacity();
            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = value;
            size++;
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
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            size--;
            items[size] = null;
            return temp;
        }
        throw new ArrayIndexOutOfBoundsException("Can`t remove element, no such index");
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null && items[i] == null || items[i].equals(t)) {
                return remove(i);
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
        if (items.length < size + 1) {
            Object[] temp = items;
            items = new Object[size * 3 / 2 + 1];
            System.arraycopy(temp, 0, items, 0, temp.length);
        }
    }

    private boolean isValidIndex(int index) {
        return index < size && index >= 0;
    }

}
