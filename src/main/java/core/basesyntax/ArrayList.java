package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_VALUE = 1.5;
    private int size;
    private T[] list;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexWhenAdd(index);
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
        checkIndex(index);
        T removed = (T) list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        remove(findIndexByElement(element));
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkIndexWhenAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void checkSize() {
        if (list.length == size) {
            T[] resizedArray = (T[]) new Object[(int) ((int) size * RESIZE_VALUE)];
            System.arraycopy(list, 0, resizedArray, 0, (int) size);
            list = resizedArray;
        }
    }

    private int findIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == element || element != null && element.equals(list[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Element" + element + "doesn't exist");
    }
}
