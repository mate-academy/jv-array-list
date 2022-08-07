package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
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
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is greater for the length of the list!");
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is greater for the length of the list!");
        }
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is greater for the length of the list!");
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is greater for the length of the list!");
        }
        T removedObject = list[index];
        removeObject(index);
        return removedObject;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        T removedObject = null;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(list[i])) {
                    index = i;
                    removedObject = list[i];
                    break;
                }
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
        list = Arrays.copyOf(list, list.length + (DEFAULT_CAPACITY >> 1));
    }

    private void removeObject(int index) {
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(list, index + 1, list, index, newSize - index);
        }
        size = newSize;
        list[size] = null;
    }
}
