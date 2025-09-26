package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_NUMERATOR = 3;
    private static final int GROWTH_DENOMINATOR = 2;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkPositionIndex(index); // allow index == size
        ensureCapacity(size + 1);
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int otherSize = list.size();
        ensureCapacity(size + otherSize);
        for (int i = 0; i < otherSize; i++) {
            elements[size + i] = list.get(i);
        }
        size += otherSize;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkElementIndex(index);
        return (T) elements[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T set(T value, int index) {
        checkElementIndex(index);
        T old = (T) elements[index];
        elements[index] = value;
        return old;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkElementIndex(index);
        T oldValue = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // clear to let GC do its work
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int idx = indexOf(element);
        if (idx == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(idx);
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
        if (minCapacity <= elements.length) {
            return;
        }
        int oldCapacity = elements.length;
        int newCapacity = (oldCapacity * GROWTH_NUMERATOR) / GROWTH_DENOMINATOR; // 1.5x
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        Object[] newArr = new Object[newCapacity];
        System.arraycopy(elements, 0, newArr, 0, size);
        elements = newArr;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
