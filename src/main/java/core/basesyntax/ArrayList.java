package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_SIZE = 10;
    private static final String OF_BOUND_MESSAGE = "Out of bound index in ArrayList";
    private static final String OF_ABSENT_MESSAGE = "Element of ArrayList ain't present";
    private T[] storage;
    private int size;

    public ArrayList() {
        this.storage = (T[]) new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        if (storage.length == size) {
            increaseSize();
        }
        storage[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (!adding(value, index)) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        T result = getting(index);
        if (result != null && result.getClass().getGenericSuperclass() == null) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
        return result;
    }

    @Override
    public void set(T value, int index) {
        if (!setting(value, index)) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
    }

    @Override
    public T remove(int index) {
        T result = removing(index);
        if (result == null) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(storage[i], element)) {
                return removing(i);
            }
        }
        throw new NoSuchElementException(OF_ABSENT_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean adding(T value, int index) {
        if (index >= 0 && index <= size) {
            List<T> tempList = null;
            if (index < size) {
                tempList = new ArrayList<>();
                for (int i = index; i < size; i++) {
                    tempList.add(get(i));
                }
            }
            size = index;
            add(value);
            addAll(tempList);
        }
        return index >= 0 && index < size;
    }

    private T getting(int index) {
        return index < 0 || index >= size ? (T) new Object() : storage[index];
    }

    private T removing(int index) {
        T result = null;
        if (index >= 0 && index < size) {
            result = storage[index];
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
            storage[--size] = null;
        }
        return result;
    }

    private boolean setting(T value, int index) {
        if (index >= 0 && index < size) {
            storage[index] = value;
        }
        return index >= 0 && index < size;
    }

    private void increaseSize() {
        int extendSize = size + (size >> 1);
        T[] tempStorage = (T[]) new Object[extendSize];
        System.arraycopy(storage, 0, tempStorage, 0, storage.length);
        storage = tempStorage;
    }
}
