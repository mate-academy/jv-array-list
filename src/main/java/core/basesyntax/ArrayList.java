package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] storage = new Object[CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == storage.length) {
            increaseCapacity();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add value on this index");
        }
        if (size == storage.length) {
            increaseCapacity();
        }

        System.arraycopy(storage, index, storage, index + 1,size - index);
        storage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if ((listSize + size) > storage.length) {
            increaseCapacity();
        }
        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForAdd(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            storage[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Cannot set value on this index" + index);
        }
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        T oldValue = get(index);

        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        remove(index);
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

    private void increaseCapacity() {
        int oldCapacity = storage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object [] increasedStorage = new Object[newCapacity];
        System.arraycopy(storage, 0, increasedStorage, 0, size);
        storage = increasedStorage;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("error");
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == storage[i]
                    || (value != null && value.equals(storage[i]))) {
                return i;
            }
        }
        throw new NoSuchElementException("Not found element like " + value);
    }
}
