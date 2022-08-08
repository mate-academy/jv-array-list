package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_FIRST_INDEX = 0;
    private static final double GROWTH_INDEX = 1.5;
    private int size;
    private int capacity;
    private T[] values;

    public ArrayList() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            values = grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == capacity) {
            values = grow();
        }
        insert(index,value);
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
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removed = values[index];
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(values, ARRAY_FIRST_INDEX, tempArray, ARRAY_FIRST_INDEX, index);
        System.arraycopy(values, index + 1, tempArray, index,size - index - 1);
        values = tempArray;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        capacity = (int) (capacity * GROWTH_INDEX);
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(values, ARRAY_FIRST_INDEX, tempArray, ARRAY_FIRST_INDEX, size);

        return tempArray;
    }

    private void insert(int index, T value) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        T[] tempArray = (T[]) new Object[capacity];
        System.arraycopy(values, ARRAY_FIRST_INDEX, tempArray, ARRAY_FIRST_INDEX, index);
        tempArray[index] = value;
        System.arraycopy(values, index, tempArray, index + 1, size - index);
        values = tempArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found");
    }
}
