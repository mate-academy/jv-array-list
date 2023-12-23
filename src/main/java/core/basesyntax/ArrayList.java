package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_ARRAY_CAPACITY = 10;
    private int size;
    private T[] objects;

    public ArrayList() {
        objects = (T[]) new Object[START_ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow(size);
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow(size);
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                "Can't find the element by index " + index);
        }
        System.arraycopy(objects, index,
                objects, index + 1,
                objects.length - (index + 1));
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int requiredSize = size + list.size();
        grow(requiredSize);
        T[] listArray = (T[]) new Object[list.size()];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = list.get(i);
        }
        System.arraycopy(listArray, 0,
                objects, size,
                listArray.length);
        size += listArray.length;
    }

    @Override
    public T get(int index) {
        findPossibleException(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        findPossibleException(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        findPossibleException(index);
        T result = objects[index];
        System.arraycopy(objects, index + 1, objects, index, objects.length - (index + 1));
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == element || objects[i] != null && objects[i].equals(element)) {
                System.arraycopy(objects, i + 1, objects, i, objects.length - (i + 1));
                size--;
                return element;
            }
        }
        throw new NoSuchElementException(
                "Can't find the element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow(int size) {
        int objectsLength = objects.length;
        while (objectsLength <= size) {
            System.arraycopy(objects, 0,
                    objects = (T[]) new Object[(int) (objects.length * 1.5)], 0,
                    objectsLength);
            objectsLength = objects.length;
        }
    }

    public void findPossibleException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't find the element by index " + index);
        }
    }
}
