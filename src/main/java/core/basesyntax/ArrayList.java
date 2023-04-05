package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = (T[]) new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resizeIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null
                && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded() {
        if (elements.length == size) {
            T[] newArray = (T[]) new Object[(int) (elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private boolean checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index: " + index + " out of bound: "
                    + size);
        }
        return false;
    }

    private boolean checkIndexForAdd(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Input index: " + index + " out of bound: "
                    + size);
        }
        return false;
    }
}
