package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] objects;
    private int size;

    public ArrayList() {
        this.objects = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == objects.length) {
            grow();
        }
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid!");
        }
        if (index == size - 1 && index == size()) {
            this.add(value);
        } else {
            shift(index);
            objects[index] = value;
            size++;
        }

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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
        T removedElement = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null) {
                if (element.equals(objects[i])) {
                    return remove(i);
                }
            } else if (objects[i] == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No Such Element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (!(index > 0 && index < size || index == 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void grow() {
        int newSize = size + (size >>> 1);
        Object[] newArray = new Object[newSize];
        System.arraycopy(objects, 0, newArray, 0, size());
        objects = newArray;
    }

    private void shift(int index) {
        Object[] newArray = new Object[size + 1];
        if (index == 0) {
            System.arraycopy(objects, 0, newArray, 1, size());
        } else {
            System.arraycopy(objects, 0, newArray, 0, index);
            System.arraycopy(objects, index, newArray, index + 1, size() - index);
        }
        objects = newArray;
    }
}
