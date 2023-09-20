package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_FACTOR = 1.5;
    private static final int NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elements = (T[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeed();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }

        if (index == size) {
            add(value);
        } else {
            growIfNeed();
            System.arraycopy(elements, index, elements, index + 1, size++ - index);
            elements[index] = value;
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
        isIndexValid(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);

        T oldValue = elements[index];
        if (index == size - 1) {
            elements[index] = null;

            size--;
            return oldValue;
        }

        System.arraycopy(elements, index + 1, elements, index, --size - index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = findElement(element);
        if (index == NOT_FOUND) {
            throw new NoSuchElementException("Element: " + element + " not found");
        }

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

    private int findElement(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (elements[i] != null && elements[i].equals(element))) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    private void growIfNeed() {
        int valuesLength = elements.length;
        if (size == valuesLength) {
            T[] extendedArray =
                    (T[]) new Object[valuesLength + (int) (valuesLength * GROW_FACTOR)];
            System.arraycopy(elements, 0, extendedArray, 0, valuesLength);
            elements = extendedArray;
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index
                    + ". Size of array is: " + size);
        }
    }
}
