package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEF_CAPACITY = 10;
    private Object[] element;
    private int capacity;
    private int size;

    public ArrayList() {
        element = new Object[DEF_CAPACITY];
    }

    private void resize(int minCapacity) {
        if (minCapacity > element.length) {
            int newCapacity = (minCapacity * 3) / 2 + 1;
            Object[] oldData = element;
            element = (T[]) new Object[newCapacity];
            System.arraycopy(oldData, 0, element, 0, size);
        }
    }

    private void realIndex(int index) {
        if (index == size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public void add(T value) {
        resize(size + 1);
        element[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        resize(size);
        System.arraycopy(element, index, element, index + 1, size - index);
        element[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public void addAll(List<T> list, Object value) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        realIndex(index);
        return (T) element[index];
    }

    @Override
    public void set(T value, int index) {
        realIndex(index);
        element[index] = value;
    }

    @Override
    public T remove(int index) {
        realIndex(index);
        T res = (T) element[index];
        System.arraycopy(element, index + 1, element, index, size - index - 1);
        size--;
        return res;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (element[i] == null || t != null && t.equals(element[i])) {
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
