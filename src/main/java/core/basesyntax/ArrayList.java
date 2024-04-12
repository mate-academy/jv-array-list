package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] defaultArray;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        defaultArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds" + index);
        }
        growIfArrayFull();
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0;i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = defaultArray[index];
        for (int i = index; i < size - 1; i++) {
            defaultArray[i] = defaultArray[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Unable to locate element");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == defaultArray.length) {
            int newCapacity = (int) (defaultArray.length * GROW_FACTOR);
            defaultArray = Arrays.copyOf(defaultArray, newCapacity);
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && defaultArray[i] == null) {
                return i;
            } else if (element != null && element.equals(defaultArray[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds" + index);
        }
    }
}
