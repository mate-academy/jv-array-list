package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW = 1.5;

    private T[] elements;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
        elements = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        resizeArray();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resizeArray();
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
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == element
                    || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        if (elements.length == size) {
            T[] newArray = (T[]) new Object[(int) (elements.length * GROW)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index:"
                    + index + " Size:" + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index:"
                    + index + " Size:" + size);
        }
    }
}
