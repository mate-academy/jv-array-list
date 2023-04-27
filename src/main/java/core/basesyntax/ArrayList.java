package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_LIST_CAPACITY = 10;
    private static final double ARRAY_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_ARRAY_LIST_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * ARRAY_MULTIPLIER);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexOutOfRange(index);
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * ARRAY_MULTIPLIER);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, index);
            newElements[index] = value;
            System.arraycopy(elements, index, newElements, index + 1, size - index);
            elements = newElements;
        } else {
            for (int i = size - 1; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
            elements[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int remainingCapacity = elements.length - size;
        if (list.size() > remainingCapacity) {
            int newCapacity = (int) (elements.length * ARRAY_MULTIPLIER);
            T[] newElements = Arrays.copyOf(elements, newCapacity);
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        for (int i = 0; i < list.size(); i++) {
            if (size == elements.length) {
                int newCapacity = (int) (elements.length * ARRAY_MULTIPLIER);
                elements = (T[]) new Object[newCapacity];
            }
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be between 0 and " + (elements.length - 1)
            );
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be between 0 and " + (elements.length - 1)
            );
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be between 0 and " + (size - 1)
            );
        }
        final T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        elements[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                final T removedElement = elements[i];
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[size] = null;
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("There is no such value");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexOutOfRange(int index) {
        String msg = "Index must be between 0 and ";
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(msg + (elements.length - 1));
        }
    }
}
