package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index);
        growIfArrayFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == this) {
            int originalSize = size;
            ensureCapacity(size + originalSize);
            for (int i = 0; i < originalSize; i++) {
                elements[size + i] = elements[i];
            }
            size += originalSize;
            return;
        }
        ensureCapacity(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        T removed = elements[index];
        int elementsMove = size - index - 1;
        if (elementsMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsMove);
        }
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        int indx = indexOf(element);
        if (indx == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(indx);
    }

    @Override
    public int size() {
        return size;
    }

    private void growIfArrayFull() {
        if (size == elements.length) {
            resize();
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null ? element == null : elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }
        int newCapacity = elements.length;
        while (newCapacity < minCapacity) {
            newCapacity = (int) (newCapacity * GROWTH_FACTOR);
        }
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size); // копіюємо тільки зайняте
        elements = newArray;
    }

    private void resize() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size); // копіюємо тільки зайняте
        elements = newArray;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index "
                            + index
                            + " out of bounds for size "
                            + size
            );
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index "
                            + index
                            + " is invalid for insertion; size "
                            + size
            );
        }
    }
}
