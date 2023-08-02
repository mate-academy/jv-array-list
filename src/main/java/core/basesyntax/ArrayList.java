package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] storage = new Object[DEFAULT_CAPACITY];

    public ArrayList() {
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfArrayFull();

        System.arraycopy(storage, index, storage, index + 1, size - index);

        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            add(element);
        }
    }

    @Override
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
    public T remove(int index) {
        checkIndex(index);

        T oldValue = (T) storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;

        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (storage[i] == element || (storage[i] != null && storage[i].equals(element))) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Not found element");
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index:" + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index:" + index);
        }
    }

    private void growIfArrayFull() {
        if (size >= storage.length) {
            storage = Arrays.copyOf(storage, storage.length + (storage.length >> 1));
        }
    }
}
