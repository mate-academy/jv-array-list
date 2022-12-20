package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growChecker();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            indexValidation(index);
        }
        growChecker();
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
        indexValidation(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        final T object = objects[index];
        size--;
        System.arraycopy(objects, index + 1, objects, index, size - index);
        objects[size] = null;
        return object;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(objects[i])) || objects[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This object don't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of bounds");
        }
    }

    private void growChecker() {
        if (size == objects.length) {
            int oldCapacity = objects.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            T[] newObjects = (T[]) new Object[newCapacity];
            System.arraycopy(objects, 0, newObjects, 0, size);
            objects = newObjects;
        }
    }
}
