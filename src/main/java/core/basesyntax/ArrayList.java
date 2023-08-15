package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        grow();
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index > size + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        } else if (index == size) {
            add(value);
        } else {
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = value;
            size++;
        }
    }

    private void grow() {
        if (size == list.length) {
            Object[] newList = new Object[size + size / 2];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
    }

    private void grow(int growingSize) {
        Object[] newList = new Object[size + growingSize];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index!");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= this.list.length) {
            grow(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T returnValue = (T) list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((list[i] == element) || (list[i] != null && list[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such value in list");
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
