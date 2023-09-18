package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
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
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }

        growIfNeed();

        if (index == size) {
            add(value);
        } else if (index == 0) {
            T[] newElements = (T[]) new Object[elements.length];
            newElements[index] = value;
            System.arraycopy(elements, 0, newElements, 1, size);
            elements = newElements;
            size++;
        } else {
            T[] newElements = (T[]) new Object[elements.length];
            System.arraycopy(elements, 0, newElements, 0, index);
            newElements[index] = value;
            System.arraycopy(elements, index, newElements, index + 1, size - index + 1);
            elements = newElements;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            growIfNeed();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }

        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }

        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index " + index);
        }

        T oldValue = elements[index];
        if (index == size - 1) {
            elements[index] = null;
        } else if (index == 0) {
            T[] newElements = (T[]) new Object[elements.length];
            System.arraycopy(elements, 1, newElements, 0, size);
            elements = newElements;
        } else {
            T[] newElements = (T[]) new Object[elements.length];
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements, index + 1, newElements, index, size - index);
            elements = newElements;
        }

        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = findElement(element);
        if (index == -1) {
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
            T[] extendedValues = (T[]) new Object[valuesLength + (int) (valuesLength * 1.5)];
            System.arraycopy(elements, 0, extendedValues, 0, valuesLength);
            elements = extendedValues;
        }
    }
}
