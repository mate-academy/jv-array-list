package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;
    private int size;
    private int capacity;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        objects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= capacity) {
            capacity = grow();
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist: " + index);
        }
        if (size == capacity) {
            capacity = grow();
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
        checkIndex(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T deletedObject = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - (index + 1));
        objects[size - 1] = null;
        size--;
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == element || (element != null && element.equals(objects[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list: "
                + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int grow() {
        capacity += capacity >> 1;
        Object[] temp = objects;
        objects = new Object[capacity];
        System.arraycopy(temp, 0, objects, 0, size);
        return capacity;
    }

    private void checkIndex(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "No element with such index in list: " + index);
        }
    }
}
