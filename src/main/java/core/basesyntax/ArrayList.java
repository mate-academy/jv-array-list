package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        this.list = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        checkSize();
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        System.arraycopy(list, index + 1, list, index, (size - 1) - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(T obj) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null ? obj == null : list[i].equals(obj)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element no found");
    }

    private void checkSize() {
        if (size == list.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (list.length + list.length / 2);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, newArray, 0, list.length);
        list = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
