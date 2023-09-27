package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREASING_FACTOR = 1.5;
    private int capacity = 10;
    private int size = 0;
    private int elementPosition = -1;
    private T[] types;

    public ArrayList() {
        types = (T[]) new Object[capacity];
    }

    public void grow() {
        capacity *= INCREASING_FACTOR;
        T[] newTypes = (T[]) new Object[capacity];
        System.arraycopy(types, 0, newTypes, 0, types.length);
        types = newTypes;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        types[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size()) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        } else if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index can mot be negative");
        }
        if (size() == capacity) {
            grow();
        }
        System.arraycopy(types, index, types, index + 1, size() - index);
        types[index] = value;
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
        if (index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        } else if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index can mot be negative");
        }
        return types[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        } else if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index can mot be negative");
        }
        types[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds");
        } else if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index can mot be negative");
        }
        if (size() == capacity) {
            grow();
        }
        T removedElement = types[index];
        System.arraycopy(types, index + 1, types, index, size() - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == types[i] || element != null && element.equals(types[i])) {
                elementPosition = i;
            }
        }
        if (elementPosition == -1) {
            throw new NoSuchElementException("element was not found");
        }
        T removedElement = types[elementPosition];
        System.arraycopy(types, elementPosition + 1, types, elementPosition,
                size() - elementPosition);
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
