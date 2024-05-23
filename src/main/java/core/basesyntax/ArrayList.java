package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elements;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        ensureCapacity(size + 1);
        shiftElementsRight(index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
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
        T oldElement = elements[index];
        shiftElementsLeft(index);
        elements[--size] = null;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int indexOf(Object o) {
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

    @SuppressWarnings("unchecked")
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = (int) (elements.length * GROWTH_FACTOR);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            T[] newElements = (T[]) new Object[newCapacity];
            copyElements(elements, newElements, size);
            elements = newElements;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void shiftElementsRight(int index) {
        if (size - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
    }

    private void shiftElementsLeft(int index) {
        if (size - 1 - index >= 0) {
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        }
    }

    private void copyElements(T[] src, T[] dest, int length) {
        for (int i = 0; i < length; i++) {
            dest[i] = src[i];
        }
    }
}
