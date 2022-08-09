package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_RANGE = 10;
    private Object[] objects;
    private int size = 0;

    public ArrayList() {
        objects = new Object[MAX_RANGE];
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
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " lager then size: " + size);
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
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        Object removedObject = objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        Object removedObject;
        for (int i = 0; i < size; i++) {
            if (element == objects[i]
                    || objects[i] != null
                    && objects[i].equals(element)) {
                removedObject = objects[i];
                remove(i);
                return (T) removedObject;
            }
        }
        throw new NoSuchElementException("Cant find " + element);
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
            Object[] newObject = new Object[(objects.length * 3 / 2)];
            System.arraycopy(objects, 0, newObject, 0, objects.length);
            objects = newObject;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " less then 0 or lager then size: " + size);
        }
    }
}
