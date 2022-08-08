package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] storage;
    private int size;

    public ArrayList() {
        this.storage = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkArraySizeAndResize();
        size++;
        checkIndex(index);
        System.arraycopy(storage, index, storage, index + 1, size - 1 - index);
        storage[index] = value;
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
        Object removed = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        --size;
        return (T) removed;
    }

    @Override
    public T remove(T element) {
        if (getIndex(element) == -1) {
            throw new NoSuchElementException(element + " not find ");
        }
        return remove(getIndex(element));
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
            throw new ArrayListIndexOutOfBoundsException("you use invalid index " + index);
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == storage[i]
                    || element != null && element.equals(storage[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkArraySizeAndResize() {
        if (size == storage.length) {
            Object[] tmp = new Object[size * 3 / 2];
            System.arraycopy(storage, 0, tmp, 0, size);
            storage = tmp;
        }
    }
}
