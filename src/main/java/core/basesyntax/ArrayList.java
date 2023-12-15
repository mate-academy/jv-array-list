package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            grow();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Specified index is invalid");
        }
        if (index == size) {
            add(value);
            return;
        }
        if (size == list.length) {
            grow();
        }
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
        T removedElement = get(index);
        if (index != size - 1) {
            System.arraycopy(list, index + 1, list, index, size - index);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int elIndex = indexOf(element);
        if (elIndex < 0) {
            throw new NoSuchElementException("Specified element is not in list");
        }
        return remove(elIndex);
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
        int newCapacity = (int) (list.length * GROWTH_FACTOR);
        T[] enlargedArray = (T[]) new Object[newCapacity];
        System.arraycopy(list, 0, enlargedArray, 0, size);
        list = enlargedArray;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == null && element == null) {
                return i;
            }
            if (list[i] != null && list[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Specified index is invalid");
        }
    }
}
