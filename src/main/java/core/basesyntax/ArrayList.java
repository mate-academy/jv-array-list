package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private int size = 0;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= items.length - 1) {
            resize((int) (items.length * MAGNIFICATION_FACTOR));
        }
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
        if (size + 1 >= DEFAULT_CAPACITY) {
            resize((int) (items.length + 1));
        }
        for (int i = size; i >= index; i--) {
            items[i + 1] = items[i];
        }
        items[index] = value;
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
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
        T deletedObject = items[index];
        if (index + 1 == items.length) {
            size--;
            return deletedObject;
        }
        System.arraycopy(items, index + 1, items, index, size-- - index);
        return deletedObject;

    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " non-existent");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 1) {
            return false;
        }
        return true;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(items, 0, newArray, 0, size);
        items = (T[]) newArray;
    }
}
