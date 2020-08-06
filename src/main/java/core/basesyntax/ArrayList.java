package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T[] storage;
    private int currentSize;

    public ArrayList() {
        currentSize = 0;
        capacity = DEFAULT_CAPACITY;
        storage = (T[]) new Object[capacity];
    }

    private T[] resize() {
        capacity = (capacity * 3) / 2 + 1;
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(storage, 0, newArray, 0, currentSize);
        return newArray;
    }

    @Override
    public void add(T value) {
        if (capacity == currentSize) {
            storage = resize();
        }
        storage[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > capacity && -1 < index) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds of " + capacity);
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
        if (index < currentSize && -1 < index) {
            return (T) storage[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < currentSize && -1 < index) {
            storage[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index < currentSize && -1 < index) {
            T toReturn = storage[index];
            System.arraycopy(storage, index + 1, storage, index, currentSize - (index + 1));
            currentSize--;
            return toReturn;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
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
}
