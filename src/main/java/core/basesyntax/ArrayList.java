package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] storage;
    private int size;

    public ArrayList() {
        storage = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        storage = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (isFilled()) {
            increaseCapacity();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bound for length " + size);
        }
        if (isFilled()) {
            increaseCapacity();
        }
        moveToRight(index);
        storage[index] = value;
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
        if (isIncorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bound for length " + size);
        }
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIncorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bound for length " + size);
        }
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIncorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is out of bound for length " + size);
        }
        final T removedElement = (T) storage[index];
        moveToLeft(index);
        storage[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == storage[i] || (storage[i] != null && storage[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity() {
        int newCapacity = storage.length / 2 + storage.length;
        updateStorage(newCapacity);
    }

    private void updateStorage(int newCapacity) {
        Object[] destination = new Object[newCapacity];
        System.arraycopy(storage, 0, destination,0, size);
        storage = destination;
    }

    private boolean isFilled() {
        return size == storage.length;
    }

    private boolean isIncorrect(int index) {
        return index < 0 || index >= size;
    }

    private boolean checkIndex(int index) {
        return index < 0 || index > size;
    }

    private void moveToRight(int from) {
        System.arraycopy(storage, from, storage, from + 1, size - from);
    }

    private void moveToLeft(int from) {
        System.arraycopy(storage, from + 1, storage, from, size - from - 1);
    }
}
