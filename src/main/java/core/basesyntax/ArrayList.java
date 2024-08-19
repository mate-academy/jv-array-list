package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAYLIST_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_ARRAYLIST_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkBoundExclusive(index);
        if (index == size || size >= elements.length) {
            ensureCapacity(size + 1);
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List can't be null");
        }
        int newSize = size + list.size();
        ensureCapacity(newSize);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                elements[size + i] = list.get(i);
            }
        }
        size = newSize;
    }

    @Override
    public T get(int index) {
        checkBoundInclusive(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkBoundInclusive(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBoundInclusive(index);
        final T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    final T removedElement = (T) elements[i];
                    for (int j = i; j < size - 1; j++) {
                        elements[j] = elements[j + 1];
                    }
                    elements[size - 1] = null;
                    size--;
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i] != null && elements[i].equals(element)) {
                    final T removedElement = (T) elements[i];
                    for (int j = i; j < size - 1; j++) {
                        elements[j] = elements[j + 1];
                    }
                    elements[size - 1] = null;
                    size--;
                    return removedElement;
                }
            }
        }
        throw new NoSuchElementException("No such element found " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity(int minCapacity) {
        int currentCapacity = elements.length;
        while (currentCapacity - minCapacity < 0) {
            currentCapacity = (currentCapacity / 2) + currentCapacity;
        }
        elements = Arrays.copyOf(elements, currentCapacity);
    }

    public void checkBoundExclusive(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range: Size is: " + size);
        }
    }

    private void checkBoundInclusive(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of range: Size is: " + size);
        }
    }

}
