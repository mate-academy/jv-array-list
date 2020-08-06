package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] storage;
    private int currentSize;

    public ArrayList() {
        currentSize = 0;
        storage = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (storage.length == currentSize) {
            storage = resize();
        }
        storage[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("");
        }
        if (storage.length == currentSize) {
            resize();
        }
        System.arraycopy(storage, index, storage, index + 1, currentSize - index);
        storage[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        outOfBoundsCheck(index);
        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsCheck(index);
        T toReturn = storage[index];
        System.arraycopy(storage, index + 1, storage, index, currentSize - (index + 1));
        currentSize--;
        return toReturn;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (storage[i] == t
                    || storage[i] != null && storage[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No Such Element Found");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private T[] resize() {
        T[] newArray = (T[]) new Object[(storage.length * 3) / 2 + 1];
        System.arraycopy(storage, 0, newArray, 0, currentSize);
        return newArray;
    }

    private void outOfBoundsCheck(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds of " + storage.length);
        }
    }
}
