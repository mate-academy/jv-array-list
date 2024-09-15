package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;

    private T[] storage;

    private int currentCapacity;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.storage = (T[]) new Object[DEFAULT_CAPACITY];
        this.currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (this.size == this.currentCapacity) {
            this.resizeStorage();
        }

        this.storage[this.size] = value;
        this.size += 1;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, false);

        if (this.size == this.currentCapacity) {
            this.resizeStorage();
        }

        int idx = this.currentCapacity - 1;

        while (idx > index) {
            this.storage[idx] = this.storage[idx - 1];
            idx -= 1;
        }

        this.storage[index] = value;
        this.size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        while (this.size + list.size() > this.currentCapacity) {
            this.resizeStorage();
        }

        for (int i = 0; i < list.size(); i += 1) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, true);

        return this.storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, true);

        this.storage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, true);

        T removedValue = null;

        for (int i = 0; i < this.size; i += 1) {
            if (i == index) {
                removedValue = this.storage[i];
            }

            if (i > index) {
                this.storage[i - 1] = this.storage[i];
            }

            if (i == this.size - 1) {
                this.storage[i] = null;
            }
        }

        this.size -= 1;

        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i += 1) {
            if (this.storage[i] == element
                    || (element != null
                    && element.equals(this.storage[i]))
            ) {
                return this.remove(i);
            }
        }

        throw new NoSuchElementException("No such element in list");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resizeStorage() {
        int newCapacity = (int) (this.currentCapacity * CAPACITY_MULTIPLIER);

        T[] newStorage = (T[]) new Object[newCapacity];

        for (int i = 0; i < currentCapacity; i += 1) {
            newStorage[i] = this.storage[i];
        }

        this.storage = newStorage;
        this.currentCapacity = newCapacity;
    }

    private void checkIndex(int index, boolean isSizeIndexForbidden) {
        if (index < 0 || index > this.size || (isSizeIndexForbidden && index == this.size)) {
            throw new ArrayListIndexOutOfBoundsException("Index must be between 0 and " + (this.size));
        }
    }
}
