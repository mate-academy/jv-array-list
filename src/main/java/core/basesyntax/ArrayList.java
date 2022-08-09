package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASING_RATE = 1.5;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("IllegalArgument: " + initCapacity);
        }
        this.elements = (T[]) new Object[initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T element) {
        resizeArray();
        elements[size] = element;
        size++;
    }

    @Override
    public void add(T element, int index) {
        if (index > elements.length || index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " - out of bounds, array size: " + size);
        }
        resizeArray();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
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
        checkIndexOutOfBounds(index);
        return elements[index];
    }

    @Override
    public void set(T element, int index) {
        checkIndexOutOfBounds(index);
        elements[index] = element;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexOutOfBounds(int index) {
        if (index > elements.length || index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " - out of bounds, array length: " + elements.length);
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeArray() {
        if (elements.length == size) {
            T[] newArray = (T[]) new Object[(int)(size * INCREASING_RATE)];
            System.arraycopy(elements, 0, newArray, 0, size);
            this.elements = (T[]) newArray;
        }
    }
}
