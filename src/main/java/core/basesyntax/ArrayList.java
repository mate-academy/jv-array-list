package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_INDEX = DEFAULT_CAPACITY + (DEFAULT_CAPACITY / 2);
    private static final String MESSAGE = "The index is out of Array size.";
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[DEFAULT_CAPACITY];
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
            throw new ArrayListIndexOutOfBoundsException(MESSAGE);
        }
        grow();
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
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
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (objects[i] == element || element != null && element.equals(objects[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element.");
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
        if (size == objects.length) {
            Object[] newObjects = new Object[objects.length * GROW_INDEX];
            System.arraycopy(objects, 0, newObjects, 0, size);
            objects = (T[]) newObjects;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE);
        }
    }
}
