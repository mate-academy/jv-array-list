package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEF_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEF_CAPACITY];
    }

    private T[] checkSize() {
        T[] newCapacity = (T[]) new Object[(elements.length * 3) / 2 + 1];
        System.arraycopy(elements, 0, newCapacity, 0, size);
        return newCapacity;
    }

    private void realIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = checkSize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        realIndex(index);
        if (size == elements.length) {
            elements = checkSize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        realIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        realIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        realIndex(index);
        T res = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return res;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null || t != null && t.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Wrong element");
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
