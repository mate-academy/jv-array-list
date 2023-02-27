package core.basesyntax;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_ARRAY_LENGTH = 10;
    private int size;
    private T[] objects;

    public ArrayList() {
        objects = (T[]) (Array.newInstance(Object.class, START_ARRAY_LENGTH));
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
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
        grow();
        Object [] clonedArray = new Object[objects.length];
        System.arraycopy(objects, 0, clonedArray, 0, index);
        clonedArray[index] = value;
        System.arraycopy(objects, index, clonedArray, index + 1, size - index);
        size++;
        objects = (T[]) (clonedArray);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validate(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        validate(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        validate(index);
        Object [] clonedArray = new Object[objects.length];
        size--;
        System.arraycopy(objects, 0, clonedArray, 0, index);
        System.arraycopy(objects, index + 1, clonedArray, index, size - index);
        T removed = objects[index];
        objects = (T[]) (clonedArray);
        return removed;
    }

    @Override
    public T remove(T element) {
        size--;
        for (int j, i = 0; i < size; i++) {
            if ((objects[i] == element) || (objects[i] != null && objects[i].equals(element))) {
                System.arraycopy(objects, i + 1, objects, i, size - i);
                return element;
            }
            if (i == size - 1) {
                throw new NoSuchElementException("Can`t find element: " + element);
            }
        }
        return null;
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
        if (objects.length == size) {
            objects = Arrays.copyOf(objects, (int) (objects.length * 1.5));
        }
    }

    private void validate(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist [" + index + "]");
        }
    }
}
