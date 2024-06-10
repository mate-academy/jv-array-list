package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] ARRAY_DATA;
    private int size;

    public ArrayList() {
        ARRAY_DATA = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        ARRAY_DATA[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkCapacity();
        ARRAY_DATA[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkCapacity();
            ARRAY_DATA[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) ARRAY_DATA[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        ARRAY_DATA[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removedElement = ARRAY_DATA[index];
        for (int i = 0; i < size - 1; i++) {
            ARRAY_DATA[i] = ARRAY_DATA[i + 1];
        }
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size < 0)
            return true;
        else
            return false;
    }

    private void checkCapacity() {
        if (size == ARRAY_DATA.length) {
            ensureCapacity();
        }
    }

    private void ensureCapacity() {
        int newCapacity = ARRAY_DATA.length * 2;
        ARRAY_DATA = Arrays.copyOf(ARRAY_DATA, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= (size + 1)) {
            throw new ArrayListIndexOutOfBoundsException("Array list index out " +
                    "of bound exception");
        }
    }
}
