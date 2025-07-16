package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private static final String ERROR_MESSAGE = "Index %d is out of bounds for size %d";
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int incomingSize = list.size();
        ensureCapacity(size + incomingSize);

        for (int i = 0; i < incomingSize; i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = (int) (elements.length * GROW_FACTOR);
            while (newCapacity < minCapacity) {
                newCapacity = (int) (newCapacity * GROW_FACTOR);
            }
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void checkIndex(int index, boolean isAddOperation) {
        boolean outOfBounds = isAddOperation
                ? (index < 0 || index > size)
                : (index < 0 || index >= size);

        if (outOfBounds) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(ERROR_MESSAGE, index, size)
            );
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d is out of bounds for add() with size %d", index, size)
            );
        }
    }
}
