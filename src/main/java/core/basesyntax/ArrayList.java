package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;

    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

        elements[index] = value;
        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (0 > index || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity <= elements.length) {
            return;
        }

        int newCapacity = (int) (elements.length * 1.5);
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);

        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index);
        elements[index] = value;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return remove(i);
                }
            } else if (element.equals(elements[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element not found: " + element);
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
