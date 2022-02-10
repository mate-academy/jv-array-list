package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] objects = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        objects[size++] = value;
        if (size == objects.length) {
            int newCapacity = objects.length + (objects.length >> 1);
            Object[] objectsNew = Arrays.copyOf(objects, newCapacity);
            objects = objectsNew;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The specified index does not exist");
        }
        Object[] objectsNew = new Object[objects.length + 1];
        System.arraycopy(objects, 0, objectsNew, 0, index);
        System.arraycopy(objects, index, objectsNew, index + 1, objects.length - index);
        objectsNew[index] = value;
        objects = objectsNew;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] objectsNew = Arrays.copyOf(objects, size + list.size());
        for (int i = size, j = 0; i < objectsNew.length; i++) {
            objectsNew[i] = list.get(j++);
        }
        objects = objectsNew;
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The specified index does not exist");
        }
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The specified index does not exist");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The specified index does not exist");
        }
        Object[] objectsNew = new Object[objects.length - 1];
        System.arraycopy(objects, 0, objectsNew, 0, index);
        System.arraycopy(objects, index + 1, objectsNew, index, objects.length - index - 1);
        T value = (T) objects[index];
        objects = objectsNew;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        boolean isPresent = false;
        int index = 0;
        for (int i = 0; i < objects.length; i++) {
            if (element == null && objects[i] == null
                    || objects[i] != null && objects[i].equals(element)) {
                isPresent = true;
                index = i;
                break;
            }
        }
        if (isPresent == false) {
            throw new NoSuchElementException("The specified element does not exist");
        }
        if (isPresent) {
            Object[] objectsNew = new Object[objects.length - 1];
            System.arraycopy(objects, 0, objectsNew, 0, index);
            System.arraycopy(objects, index + 1, objectsNew, index, objects.length - index - 1);
            objects = objectsNew;
            size--;
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }
}
