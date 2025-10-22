package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void checkIndexForAccess(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds.");
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + elements.length / 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            return;
        } else {
            ensureCapacity(size + list.size());
            for (int i = 0; i < list.size(); i++) {
                elements[size + i] = list.get(i);
            }
            size += list.size();
        }
    }

    @Override
    public T get(int index) {
        checkIndexForAccess(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForAccess(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForAccess(index);
        final Object value = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        elements[size - 1] = null;
        size--;
        return (T) value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) {
                    return remove(i);
                }
            } else {
                if (elements[i] != null && elements[i].equals(element)) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found in the list.");
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
