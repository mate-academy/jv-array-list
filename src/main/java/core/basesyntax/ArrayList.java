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
        if (storage.length < size + list.size()) {
            increaseCapacity(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            storage[size++] = list.get(i);
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
        createNewStorage(newCapacity);
    }

    private void increaseCapacity(int newCapacity) {
        createNewStorage(newCapacity);
    }

    private void createNewStorage(int newCapacity) {
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
        for (int i = size - 1; i >= from; i--) {
            storage[i + 1] = storage[i];
        }
    }

    private void moveToLeft(int from) {
        for (int i = from; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }
}
