package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
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
        checkIndex(index, "to add");
        if (size == list.length) {
            grow();
        }
        if (size > index) {
            System.arraycopy(list, index, list, index + 1, size);
        }
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
        checkIndex(index, "actually");
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, "actually");
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, "actually");
        T removedObject = list[index];
        removeObject(index);
        return removedObject;
    }

    @Override
    public T remove(T element) {
        T removedObject = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(list[i])) {
                index = i;
                removedObject = list[i];
                break;
            } else if (element == null && list[i] == null) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("The element was not found");
        }
        removeObject(index);
        return removedObject;
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
        T[] temporaryArray = list;
        list = (T[]) new Object[list.length + (DEFAULT_CAPACITY >> 1)];
        System.arraycopy(temporaryArray, 0, list, 0, size);
    }

    private void checkIndex(int index, String operation) {
        switch (operation) {
            case "to add":
                if (index > size || index < 0) {
                    throw new ArrayListIndexOutOfBoundsException(
                            "The index is greater for the length of the list!");
                }
                break;
            case "actually":
                if (index >= size || index < 0) {
                    throw new ArrayListIndexOutOfBoundsException(
                            "The index is greater for the length of the list!");
                }
                break;
            default:
        }
    }

    private void removeObject(int index) {
        if (size - 1 > index) {
            System.arraycopy(list, index + 1, list, index, size - 1 - index);
        }
        size--;
        list[size] = null;
    }
}
