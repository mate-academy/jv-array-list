package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;

    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        Object[] newElements = new Object[(int) (elements.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            indexOutOfBounds(index);
        }
    }

    private void indexOutOfBounds(int index) {
        throw new ArrayListIndexOutOfBoundsException(
                String.format("Index '%d' out of bounds for size '%d'", index, elements.length)
        );
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            indexOutOfBounds(index);
        }
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T element = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException(String.format("Element '%s' not found", elements));
        }
        return remove(index);
    }

    private int indexOf(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i] || (element != null && element.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
