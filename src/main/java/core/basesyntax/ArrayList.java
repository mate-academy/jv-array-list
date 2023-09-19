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

        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexValid(index, index > size);

        if (index == size) {
            add(value);
        } else {
            growIfNeed();
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
        isIndexValid(index, index >= size);

        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index, index >= size);

        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index, index >= size);

        T oldValue = elements[index];
        if (index == size - 1) {
            elements[index] = null;
        } else if (index == 0) {
            System.arraycopy(elements, 1, elements, 0, size);
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }

        size--;
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
            T[] extendedValues =
                    (T[]) new Object[valuesLength + (int) (valuesLength * GROW_FACTOR)];
            System.arraycopy(elements, 0, extendedValues, 0, valuesLength);
            elements = extendedValues;
        }
    }

    private void isIndexValid(int index, boolean accordingToSize) {
        if (index < 0 || accordingToSize) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }
    }
}
