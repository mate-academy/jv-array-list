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
            increaseCapacity();
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The specified index does not exist");
        }
        if (size == objects.length) {
            increaseCapacity();
        }
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
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
        T value = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
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
            System.arraycopy(objects, index + 1, objects, index, size - index);
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
        return size == 0;
    }

    public void increaseCapacity() {
        int newCapacity = objects.length + (objects.length >> 1);
        Object[] objectsNew = Arrays.copyOf(objects, newCapacity);
        objects = objectsNew;
    }
}
