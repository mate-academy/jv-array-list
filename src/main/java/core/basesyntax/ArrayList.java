package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;
    private int capacity = 10;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkInsertIndex(index);
        ensureCapacity(size + 1);

        if (index < size) {
            shiftRightFromIndex(index);
        }

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }

        size += list.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkAccessIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkAccessIndex(index);
        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkAccessIndex(index);

        T removeElement = (T) elements[index];
        shiftLeftFromIndex(index);
        size--;
        return removeElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    shiftLeftFromIndex(i);
                    size--;
                    return null;
                }
            } else if (element.equals(elements[i])) {
                T removed = (T) elements[i];
                shiftLeftFromIndex(i);
                size--;
                return removed;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void shiftRightFromIndex(int index) {
        for (int i = size; i >= index; i--) {
            if (i > 0) {
                elements[i] = elements[i - 1];
            }
        }
    }

    private void shiftLeftFromIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            while (capacity < minCapacity) {
                capacity = capacity + (capacity >> 1);
            }
            Object[] temp = new Object[capacity];
            for (int i = 0; i < size; i++) {
                temp[i] = elements[i];
            }
            elements = temp;
        }
    }

    private void checkAccessIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Allowed range: 0 to " + (size - 1)
            );
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Allowed range for add: 0 to " + size
            );
        }
    }
}
