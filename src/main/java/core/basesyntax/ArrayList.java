package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        grow();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Try another index");
        }
        size++;
        grow();
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
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
            throw new ArrayListIndexOutOfBoundsException("Try another index");
        }
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Try another index");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Try another index");
        }
        T removeObj = objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return removeObj;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (objects[i] != null && objects[i].equals(element) || objects[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element present");
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
        if (size >= objects.length) {
            T[] secondObjects = (T[]) new Object[objects.length * 3 / 2];
            System.arraycopy(objects, 0, secondObjects, 0, objects.length);
            objects = secondObjects;
        }
    }
}
