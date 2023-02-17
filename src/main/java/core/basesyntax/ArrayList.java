package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_SIZE = 10;
    private static final float INCREASE_VOLUME = 1.5f;
    private static final String OF_BOUND_MESSAGE = "Out of bound index in ArrayList";
    private static final String OF_ABSENT_MESSAGE = "Element of ArrayList ain't present";
    private T[] storage;
    private int pointer;

    public ArrayList() {
        this.storage = (T[]) new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        if (storage.length == pointer) {
            increaseSize();
        }
        storage[pointer++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > pointer) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
        List<T> tempList = new ArrayList<>();
        for (int i = index; i < pointer; i++) {
            tempList.add(get(i));
        }
        storage[index] = value;
        pointer = index + 1;
        addAll(tempList);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= pointer) {
            throw new ArrayListIndexOutOfBoundsException(OF_BOUND_MESSAGE);
        }
        T result = storage[index];
        System.arraycopy(storage, index + 1, storage, index, pointer - index - 1);
        storage[--pointer] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < pointer; i++) {
            if (storage[i] == element || (storage[i] != null && storage[i].equals(element))) {
                T result = storage[i];
                System.arraycopy(storage, i + 1, storage, i, pointer - i - 1);
                storage[--pointer] = null;
                return result;
            }
        }
        throw new NoSuchElementException(OF_ABSENT_MESSAGE);
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    private void increaseSize() {
        int extendSize = (int) (INCREASE_VOLUME * pointer);
        T[] tempStorage = (T[]) new Object[extendSize];
        System.arraycopy(storage, 0, tempStorage, 0, storage.length);
        storage = tempStorage;
    }
}
