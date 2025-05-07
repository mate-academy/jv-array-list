package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Choose index less or equal than " + size
                    + " of ArrayList<T>. This index incorrect " + index);
        }
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.size() == 0) {
            return; // No need to add elements if the list is null or empty
        }
        int additionalLength = list.size();
        checkSizeForAdditional(additionalLength); // Ensure enough space for all elements

        for (int i = 0; i < additionalLength; i++) {
            elements[size++] = list.get(i); // Add elements directly and increment size
        }
    }

    @Override
    public T get(int index) {
        checkTheIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkTheIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkTheIndex(index);
        T oldValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("ArrayList doesn't consist of the element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if there is enough space for new elements, and grow if needed
    private void checkSize() {
        if (size == elements.length) {
            grow();
        }
    }

    // Ensure enough space for additional elements
    private void checkSizeForAdditional(int additionalLength) {
        while (size + additionalLength > elements.length) {
            grow();
        }
    }

    // Resize the internal array when the capacity is exceeded
    private void grow() {
        int newCapacity = (int) (elements.length * INCREASE_MULTIPLIER);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkTheIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Choose index less or equal than " + size
                    + " of ArrayList<T>. This index incorrect " + index);
        }
    }
}
