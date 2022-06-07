package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int capacity;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }

    @Override
    public void add(T value) {
        increaseCapacity(capacity * 15);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index Out Of Bounds");
        }
        increaseCapacity(capacity * 15);
        if (size - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1,
                    size - index);
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        increaseCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index Out Of Bounds");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index Out Of Bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index Out Of Bounds");
        }
        final T deletedValue = elements[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(elements, index + 1, elements,
                    index, size - 1 - index);
        }
        elements[size - 1] = null;
        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elements[i]) || element == elements[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity(int newSize) {
        if (size == capacity) {
            capacity = newSize;
            T[] newElements = (T[]) new Object[capacity];
            if (size >= 0) {
                System.arraycopy(elements, 0, newElements, 0, size);
            }
            elements = newElements;
        }
    }
}
