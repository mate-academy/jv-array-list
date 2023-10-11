package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object [] storage = new Object[10];
    private int size = 0;

    private void validateIndex(int index) {
        if ((index < 0) || (index > size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Not found index: " + index);
        }
    }

    private void increaseStorage() {
        int oldCapacity = storage.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object [] increasedStorage = new Object[newCapacity];
        System.arraycopy(storage, 0, increasedStorage, 0, size);
        storage = increasedStorage;
    }

    @Override
    public void add(T value) {
        if (size == storage.length) {
            increaseStorage();
        }
        storage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        validateIndex(index);
        if (size + 1 == storage.length) {
            increaseStorage();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if ((listSize + size) > storage.length) {
            increaseStorage();
        }
        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        int tailLength = size - index;
        T value = get(index);
        if (tailLength > 0) {
            System.arraycopy(storage, index + 1, storage, index, tailLength - 1);
            storage[size - 1] = null;
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        remove(index);
        return element;
    }

    private int indexOf(T value) {
        for (int i = 0; i < size(); i++) {
            if ((value == storage[i])
                    || ((value != null) && (value.equals(storage[i])))) {
                return i;
            }
        }
        throw new NoSuchElementException("Not found element with value: "
                + value);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
