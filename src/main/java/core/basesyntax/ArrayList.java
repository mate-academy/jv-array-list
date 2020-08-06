package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private T[] storage;
    private int current = 0;

    public ArrayList() {
        storage = (T[]) new Object[capacity];
    }

    private T[] resize() {
        capacity = (capacity * 3) / 2 + 1;
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(storage, 0, newArray, 0, current);
        return newArray;
    }

    @Override
    public void add(T value) {
        if (capacity == current) {
            storage = resize();
        }
        storage[current++] = value;
    }

    @Override
    public void add(T value, int index) {
        while (index > capacity) {
            storage = resize();
        }
        System.arraycopy(storage, index, storage, index + 1, current - index);
        storage[index] = value;
        current++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < current) {
            return (T) storage[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < current) {
            storage[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index < current) {
            T toReturn = storage[index];
            System.arraycopy(storage, index + 1, storage, index, current - (index + 1));
            current--;
            return toReturn;
        } else {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
        }

    }

    @Override
    public T remove(T t) {
        int index = -1;
        for (int i = 0; i < current; i++) {
            if (storage[i] == t
                    || storage[i] != null && storage[i].equals(t)) {
                index = i;
            }
        }
        if (index != -1) {
            return remove(index);
        }
        throw new NoSuchElementException("No Such Element Found");
    }

    @Override
    public int size() {
        return current;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
