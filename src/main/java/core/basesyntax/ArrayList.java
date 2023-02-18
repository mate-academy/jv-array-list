package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity;
    private T[] entries;
    private int size;

    public ArrayList() {
        entries = (T[]) new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == currentCapacity) {
            growSimple();
            T[] tempEntries = (T[]) new Object[currentCapacity];
            System.arraycopy(entries, 0, tempEntries, 0, size);
            entries = tempEntries;
        }
        entries[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size && size < currentCapacity) {
            entries[index] = value;
        } else if (size == currentCapacity) {
            checkIndex(index);
            growSimple();
            T[] tempEntries = (T[]) new Object[currentCapacity];
            System.arraycopy(entries, index, tempEntries, index + 1, size - index);
            if (index > 0) {
                System.arraycopy(entries, 0, tempEntries, index - 1, index - 1);
            }
            tempEntries[index] = value;
            entries = tempEntries;
        } else {
            checkIndex(index);
            System.arraycopy(entries, index, entries, index + 1, size - index);
            entries[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return entries[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        entries[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T foundObject = entries[index];
        System.arraycopy(entries, index + 1, entries, index,--size - index);
        return foundObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(entries[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " is invalid index for size " + size);
        }
    }

    private void growSimple() {
        currentCapacity += (currentCapacity >> 1);
    }
}
