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
            this.grow();
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
            throw new ArrayListIndexOutOfBoundsException("index: " + index + "size: " + size);
        }
        if (index <= size) {
            Object[] newObjectsArray = new Object[objects.length + 1];
            System.arraycopy(objects, 0, newObjectsArray, 0, index);
            newObjectsArray[index] = value;
            System.arraycopy(objects, index, newObjectsArray, index + 1, objects.length - index);
            objects = newObjectsArray;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newObjectsArray;
        if (list != null) {
            int additionalSize = list.size();
            if (additionalSize > 0) {
                newObjectsArray = new Object[size + additionalSize];
                System.arraycopy(objects, 0, newObjectsArray, 0, size);
                for (int i = 0; i < additionalSize; i++) {
                    newObjectsArray[size + i] = list.get(i);
                }
                objects = newObjectsArray;
            }
            size += additionalSize;
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
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
        T removedObject;
        Object[] newObjectsArray;
        for (int i = 0; i < size; i++) {
            if (element == objects[i]
                    || (objects[i] != null && objects[i].equals(element))) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not finded!");
        } else {
            removedObject = (T) objects[index];
            newObjectsArray = new Object[objects.length];
            System.arraycopy(objects, 0, newObjectsArray, 0, index);
            System.arraycopy(objects, index + 1, newObjectsArray, index,
                    objects.length - (index + 1));
            objects = newObjectsArray;
            size--;
        }
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
        int newSizeArray = (int) (objects.length * 1.5);
        Object[] newObjectsArray = new Object[newSizeArray];
        System.arraycopy(objects, 0, newObjectsArray, 0, objects.length);
        this.objects = newObjectsArray;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + " size: " + size);
        }
    }
}

