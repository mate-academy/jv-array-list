package core.basesyntax;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 3 / 2 + 1;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public boolean add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
        return true;
    }

    public void add(T value, int index) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        for (T element : list) {
            add(element);
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    public void set(T value, int index) {
        rangeCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return oldValue;
    }

    public boolean remove(Object element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
            return true;
        } else {
            throw new NoSuchElementException("Element not found");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static class ArrayListIndexOutOfBoundsException extends IndexOutOfBoundsException {
        public ArrayListIndexOutOfBoundsException(String s) {
            super(s);
        }
    }
}
