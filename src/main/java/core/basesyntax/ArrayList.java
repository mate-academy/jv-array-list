package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index entered.");
        }
        grow();
        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size + 1);
        }
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
        final T removedElement = (T) elements[index];
        elements[index] = null;
        if (index != size - 1) {
            if (index == 0) {
                System.arraycopy(elements, index + 1, elements, index, size - 1);
            } else {
                System.arraycopy(elements, index, elements, index - 1, size - 1);
            }
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || elements[i] != null
                    && elements[i].equals(element)) {
                elements[i] = null;
                if (i == 0) {
                    System.arraycopy(elements, i + 1, elements, i, size - 1);
                } else {
                    System.arraycopy(elements, i, elements, i - 1, size - 1);
                }
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No such element in ArrayList.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size + 1 < elements.length) {
            return;
        }
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index entered.");
        }
    }
}

