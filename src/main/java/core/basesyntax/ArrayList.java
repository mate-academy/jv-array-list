package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_CAPACITY = 10;
    public static final String NEGATIVE_CAPACITY_MESSAGE = "Cannot create ArrayList with negative capacity";
    public static final String NO_SUCH_ELEMENT_MESSAGE = "There is no such element in the list - ";
    public static final String OUT_OF_BOUNDS_MESSAGE = "Index out of bounds";

    private Object[] storage;
    private int size = 0;

    public ArrayList() {
        storage = new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity >= 0) {
            storage = new Object[capacity];
        } else {
            throw new NegativeCapacityException(NEGATIVE_CAPACITY_MESSAGE);
        }
    }

    @Override
    public void add(T value) {
    //TODO Appends the specified element to the end of this list.

    }

    @Override
    public void add(T value, int index) {
    //TODO Inserts the specified element at the specified position in this list.
    }

    @Override
    public void addAll(List<T> list) {
    // Appends all of the elements in the specified collection to the end of this list,
        //  in the order that they are returned by the specified collection's Iterator.
        int requiredSize = size + list.size();
        if (requiredSize > storage.length) {
            increaseCapacity(requiredSize);
        }
        Object[] temp = new Object[storage.length];
        System.arraycopy(storage, 0, temp, 0, size);
        storage = temp;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        T obj;
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        } else {
            obj = (T) storage[index];
            reduceCapacity(index);
        }
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

    private void reduceCapacity(int index) {
        Objects[] temp = new Objects[storage.length];
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
