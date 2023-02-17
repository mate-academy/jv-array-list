package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int capacity;
    private Object[] storage;
    private int size;

    public ArrayList() {
        this.storage = new Object[DEFAULT_SIZE];
        this.capacity = DEFAULT_SIZE;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than 0 " + index);
        } else if (index > size + 1) {
            throw new ArrayListIndexOutOfBoundsException("You can add an element from 0 to "
                    + (size + 1) + " index!");
        }
        checkCapacity();
        Object[] tempArray = new Object[size + 1];
        System.arraycopy(storage, 0, tempArray, 0, index);
        tempArray[index] = value;
        System.arraycopy(storage, index, tempArray, index + 1, size - index);
        storage = tempArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] tempArray = new Object[size + list.size()];
        System.arraycopy(storage, 0, tempArray, 0, size);
        for (int i = 0; i < list.size(); i++) {
            tempArray[size + i] = list.get(i);
        }
        storage = tempArray;
        size += list.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        storage[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
        Object[] copy = new Object[size - 1];
        final Object oldObject = storage[index];
        System.arraycopy(storage, 0, copy, 0, index);
        if (size - (index + 1) >= 0) {
            System.arraycopy(storage, index + 1, copy, index, size - (index + 1));
        }
        storage = copy;
        size--;
        return (T) oldObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (Objects.equals(storage[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There are no such elements in the storage!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        capacity = size + (size >> 1);
        Object[] biggerStorage = new Object[capacity];
        System.arraycopy(storage, 0, biggerStorage, 0, size);
        return biggerStorage;
    }

    private void checkCapacity() {
        if (size == capacity) {
            storage = grow();
        }
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than 0 " + index);
        } else if (index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Our storage has only "
                    + (size) + " objects!");
        }
    }
}
