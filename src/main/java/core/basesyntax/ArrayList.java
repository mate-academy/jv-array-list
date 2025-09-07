package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NUMERATOR = 3;
    private static final int DENOMINATOR = 2;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void grow(int minCapacity) {
        if (elements.length >= minCapacity) {
            return;
        }
        int newCapacity = (elements.length * NUMERATOR / DENOMINATOR);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow(size + 1);
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index
                            + " out of bounds for size "
                            + size
                            + " ,Element not found: "
                            + value);
        }
        if (size == elements.length) {
            grow(size + 1);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        grow(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        @SuppressWarnings("unchecked")
                T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
                @SuppressWarnings("unchecked")
                        T removed = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return removed;
            }
        }
        throw new NoSuchElementException(element + " not found");
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
