package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] list;
    private int size = 0;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            list = grow();
        }

        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == list.length) {
            list = grow();
        }

        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Cannot add an element to an absent index");
        }

        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            this.list[size + i] = list.get(i);
        }

        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        final T removedValue = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        list[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == element || (element != null && element.equals(list[i]))) {
                return remove(i);

            }
        }
        throw new NoSuchElementException("Element is not in the list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        int newCapacity = list.length + (list.length >> 1);
        T[] newList = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, newList, 0, list.length);
        return newList;
    }

    private T[] grow(int capacity) {
        T[] newList = (T[]) new Object[capacity];
        System.arraycopy(list, 0, newList, 0, list.length);
        return newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (list.length < minCapacity) {
            list = grow(minCapacity);
        }
    }
}
