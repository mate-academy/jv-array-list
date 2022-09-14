package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_INDEX = 1.5;
    private int size;
    private T[] elements;

    @SuppressWarnings("unchecked ")
    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = (T[]) new Object[initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            if (index < 0 || index >= size) {
                throw new ArrayListIndexOutOfBoundsException("index > size" + size + index);
            }
            resizeIfNeeded();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(" index > size");
        }
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findValue(element);
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

    private int findValue(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException(("Element " + element + " is not found"));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("size < index ");
        }
    }

    @SuppressWarnings("unchecked")
    private void resizeIfNeeded() {
        if (size == elements.length) {
            T[] newArray = (T[]) new Object[(int) (elements.length * GROWTH_INDEX)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }
}
