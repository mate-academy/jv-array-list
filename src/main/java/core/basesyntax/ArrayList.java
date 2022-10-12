package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    public static final String NO_SUCH_ELEMENT_MESSAGE = "There is no such element in the list - ";
    public static final String OUT_OF_BOUNDS_MESSAGE = "Index out of bounds";

    private Object[] storage;
    private int size = 0;

    public ArrayList() {
        storage = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index - 1 == size) {
            add(value);
        } else {
            ensureCapacity();
            ensureAddition(index);
            Object[] temp = new Object[storage.length];
            System.arraycopy(storage, 0, temp, 0, index);
            temp[index] = value;
            System.arraycopy(storage, index, temp, index + 1, size - index);
            storage = temp;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int requiredSize = size + list.size();
        if (requiredSize > storage.length) {
            increaseCapacity(requiredSize);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        ensurePosition(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        ensurePosition(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        ensurePosition(index);
        T obj = (T) storage[index];
        reduceCapacity(index);
        return obj;
    }

    @Override
    public T remove(T element) {
        T obj;
        for (int i = 0; i < size; i++) {
            obj = (T) storage[i];
            if (obj == element || obj != null && obj.equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == storage.length) {
            increaseCapacity(storage.length);
        }
    }

    private void ensurePosition(int index) {
        if (index < 0 || index > size - 1) {
            showOutOfBounds();
        }
    }

    private void ensureAddition(int index) {
        if (index < 0 || index > size) {
            showOutOfBounds();
        }
    }

    private void showOutOfBounds() {
        throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
    }

    private void reduceCapacity(int index) {
        Object[] temp = new Object[storage.length - 1];
        System.arraycopy(storage, 0, temp, 0, index);
        System.arraycopy(storage, index + 1, temp, index, size - index - 1);
        storage = temp;
        size--;
    }

    private void increaseCapacity(int requiredSize) {
        int capacity = requiredSize + requiredSize / 2;
        Object[] temp = new Object[capacity];
        System.arraycopy(storage, 0, temp, 0, size);
        storage = temp;
    }
}
