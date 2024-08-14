package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_SHIFT_STEP = 1;
    private static final int HALF_CAPACITY = 2;
    private static final int ZERO_POSITION = 0;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for add operation");
        }
        resize();
        System.arraycopy(elements, index, elements,
                index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
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
        T value = elements[index];
        System.arraycopy(elements, index + ELEMENT_SHIFT_STEP,
                elements, index, size - index - ELEMENT_SHIFT_STEP);
        elements[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element" + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (elements.length == size) {
            int newCapacity = (int) (elements.length * HALF_CAPACITY);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, ZERO_POSITION,
                    newElements, ZERO_POSITION, size);
            elements = newElements;
        }
    }

    private void ensureCapacity() {
        if (size >= elements.length) {
            resize();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is out of bounds, size: " + size);
        }
    }
}
