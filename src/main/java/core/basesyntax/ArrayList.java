package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 1.5;
    private int increaseCapacity = 16;
    private T[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.table = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == increaseCapacity) {
            increaseArray();
        }
        table[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "non exist");
        }
        if (size == increaseCapacity) {
            increaseArray();
        }
        System.arraycopy(table, index, table, index + 1, size - index);
        table[index] = value;
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
        return table[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        table[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removeValue = table[index];
        System.arraycopy(table, index + 1, table, index, size - index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void increaseArray() {
        int capacity = (int) (increaseCapacity * LOAD_FACTOR);
        T[] currentArray = (T[]) new Object[capacity];
        System.arraycopy(table, 0, currentArray, 0, size);
        table = currentArray;
        increaseCapacity = capacity;
    }

    private int findIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, table[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("There is no such element in the list, " + element);
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "non exist");
        }
    }
}

