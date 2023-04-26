package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int size = 0;
    private T[] objects;

    public ArrayList() {
        objects = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        increasingTheSize();
        objects[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
        increasingTheSize();
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            increasingTheSize();
            objects[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
        final T removedObject = objects[index];
        objects[index] = null;
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(objects[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increasingTheSize() {
        if (objects.length == size) {
            T[] increasedObjectsArray = (T[]) new Object[(int) (objects.length * 1.5)];
            System.arraycopy(objects, 0, increasedObjectsArray, 0, objects.length);
            objects = increasedObjectsArray;
        }
    }
}
