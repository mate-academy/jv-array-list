package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int INITIAL_SIZE = 0;
    private static final double GROW_INDEX = 1.5;
    private Object[] storage;
    private int size;

    public ArrayList() {
        storage = new Object[INITIAL_CAPACITY];
        size = INITIAL_SIZE;
    }

    @Override
    public void add(T value) {
        if (storage.length == size) {
            grow();
        }

        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element "
                    + value + " by index " + index);
        }

        if (storage.length == size) {
            grow();
        }

        if (index == size) {
            add(value);
        } else {
            T currentElement = (T) storage[index];
            storage[index] = value;

            for (int i = index + 1; i <= size; i++) {
                T nextElement = (T) storage[i];
                storage[i] = currentElement;
                currentElement = nextElement;
            }
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (storage.length == size) {
                grow();
            }
            storage[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (!checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't element by index " + index);
        }

        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (!checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't set value "
                    + value + " by index " + index);
        }

        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element by index" + index);
        }

        final T removedElement = (T) storage[index];

        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i] == null && element == null
                    || storage[i] != null && storage[i].equals(element)) {
                index = i;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Can't remove this element " + element);
        }

        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;

        size--;

        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] newStorage = new Object[(int) (storage.length * GROW_INDEX)];

        System.arraycopy(storage, 0, newStorage, 0, size);

        storage = newStorage;
    }

    private boolean checkIndex(int index) {
        return index >= 0 && index < size;
    }
}
