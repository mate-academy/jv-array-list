package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds" + index);
        }
        growIfArrayFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        checkIndexBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements,index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Unable to locate element");
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

    private void growIfArrayFull() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * GROW_FACTOR);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null
                    || (element != null && element.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds" + index);
        }
    }
}
