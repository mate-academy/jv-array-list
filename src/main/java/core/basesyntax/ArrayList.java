package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= objects.length) {
            grow();
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size >= objects.length) {
            this.grow();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + " out of bounds for size: " + size);
        }
        Object[] newObjectsArray = new Object[objects.length + 1];
        System.arraycopy(objects, 0, newObjectsArray, 0, index);
        newObjectsArray[index] = value;
        System.arraycopy(objects, index, newObjectsArray, index + 1, objects.length - index);
        objects = newObjectsArray;
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
        Object[] newObjectsArray = new Object[objects.length];
        System.arraycopy(objects, 0, newObjectsArray, 0, index);
        System.arraycopy(objects, index + 1, newObjectsArray, index, objects.length - (index + 1));
        T removedObject = (T) objects[index];
        objects = newObjectsArray;
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == objects[i]
                    || (objects[i] != null && objects[i].equals(element))) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not found!");
        }
        return remove(index);
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
        int newSizeArray = (int) (objects.length * 1.5);
        Object[] newObjectsArray = new Object[newSizeArray];
        System.arraycopy(objects, 0, newObjectsArray, 0, objects.length);
        this.objects = newObjectsArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + " out of bounds for size: " + size);
        }
    }
}

