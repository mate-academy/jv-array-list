package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int MAX_START_LENGTH = 10;
    private int size;
    private T[] objects;

    public ArrayList() {
        objects = (T[]) new Object[MAX_START_LENGTH];
    }

    private void upgradeObjectArray() {
        T[] newLength = (T[]) new Object[objects.length + objects.length / 2];
        System.arraycopy(objects, 0, newLength, 0, size);
        objects = newLength;
    }

    @Override
    public void add(T value) {
        if (objects.length == size) {
            upgradeObjectArray();
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct ");
        }
        if (objects.length == size) {
            upgradeObjectArray();
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Default index");
        }
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index  is not correct");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct ");
        }
        T oldObjects = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return oldObjects;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((objects[i] != null && objects[i].equals(element))
                    || objects[i] == element) {
                System.arraycopy(objects, i + 1, objects, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Is not correct element ");
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
