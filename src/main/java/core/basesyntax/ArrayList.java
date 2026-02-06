package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROWTH_FACTOR = 1.5f;
    private static final int MINIMUM_GROWTH_AMOUNT = 1;

    private int size;
    private Object[] objects;

    public ArrayList() {
        size = 0;
        objects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();

        int elementsToMove = size - index;
        if (elementsToMove > 0) {
            System.arraycopy(objects, index, objects, index + 1, elementsToMove);
        }

        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return removeAt(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (areEqual(objects[i], element)) {
                return removeAt(i);
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // ----------------- helpers -----------------

    private void ensureCapacity() {
        if (size == objects.length) {
            int newCapacity = (int) (objects.length * GROWTH_FACTOR) + MINIMUM_GROWTH_AMOUNT;
            Object[] copy = new Object[newCapacity];
            System.arraycopy(objects, 0, copy, 0, objects.length);
            objects = copy;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound: " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound: " + index);
        }
    }

    @SuppressWarnings("unchecked")
    private T elementAt(int index) {
        return (T) objects[index];
    }

    private boolean areEqual(Object first, Object second) {
        if (first == null) {
            return second == null;
        }
        return first.equals(second);
    }

    private T removeAt(int index) {
        final T removed = elementAt(index);

        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(objects, index + 1, objects, index, elementsToMove);
        }

        objects[--size] = null;
        return removed;
    }
}

