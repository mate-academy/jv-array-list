package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            extendArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Failed to get element for index %d", index));
        }
        if (size == elements.length) {
            extendArray();
        }
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
        final T result = elements[index];
        System.arraycopy(elements, index + 1, elements,
                index, elements.length - 1 - index);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove the element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void reindexArray(int srcPos, int destPos) {
        Object[] newElements = new Object[(int) (elements.length * MAGNIFICATION_FACTOR)];
        System.arraycopy(elements,srcPos, elements, destPos, size);
    }

    private void extendArray() {
        Object[] newElements = new Object[(int) (elements.length * MAGNIFICATION_FACTOR)];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = (T[]) newElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Failed to get element for index %d", index));
        }
    }
}
