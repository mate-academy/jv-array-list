package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE = 10;
    private Object[] objects = new Object[DEFAULT_SIZE];
    private int size = 0;

    @Override
    public void add(T value) {
        resizeArrayIfNeeded();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexBounds(index);
        resizeArrayIfNeeded();
        Object[] objectsCopy = new Object[objects.length + 1];
        System.arraycopy(objects, 0, objectsCopy, 0, index);
        System.arraycopy(objects, index, objectsCopy, index + 1, size);
        objects = objectsCopy;
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resizeArrayIfNeeded();
            objects[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index + 1);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index + 1);
        resizeArrayIfNeeded();
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index + 1);
        Object[] objectsCopy = new Object[objects.length];
        System.arraycopy(objects, 0, objectsCopy, 0, index);
        System.arraycopy(objects, index + 1, objectsCopy, index, size - index - 1);
        Object removed = objects[index];
        objects = objectsCopy;
        size--;
        return (T) removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((objects[i] != null && objects[i].equals(element))
                    || (element == null && objects[i] == null)) {
                Object[] objectsCopy = new Object[objects.length];
                System.arraycopy(objects, 0, objectsCopy, 0, i);
                System.arraycopy(objects, i + 1, objectsCopy, i, size - i - 1);
                Object removed = objects[i];
                objects = objectsCopy;
                size--;
                return (T) removed;
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

    private void resizeArrayIfNeeded() {
        if (size == objects.length) {
            Object[] objectsCopy = new Object[(int) (objects.length * 1.5)];
            System.arraycopy(objects, 0, objectsCopy, 0, objects.length);
            objects = objectsCopy;
        }
    }

    private void checkIndexBounds(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
    }
}
