package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] list;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_SIZE];
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
        size++;
        if (size == list.length) {
            grow();
        }
        if (checkIndex(index)) {
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("The element by index was not added!");
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
        if (checkIndex(index)) {
            return list[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Cant get element by index!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            list[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Cant set element by index!");
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            final T element = list[index];
            System.arraycopy(list, index + 1, list, index, size - index - 1);
            list[size - 1] = null;
            size--;
            return element;
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    "Cant delete element from the list by index");
        }

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || list[i] != null && list[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Cant delete entered element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0;
    }

    private void grow() {
        T[] data = list;
        list = (T[]) new Object[list.length * 3 / 2];
        System.arraycopy(data, 0, list, 0, size);
    }
}
