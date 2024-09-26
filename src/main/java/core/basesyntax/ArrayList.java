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

    public void growIfArrayIsFull() {
        if (size == elements.length) {
            int multiplalier = Math.round(elements.length * 1.5f);
            System.arraycopy(elements, 0, elements = new Object[multiplalier], 0, size);
        }
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index
                    + ", Size: " + size);
        } else {
            growIfArrayIsFull();
            System.arraycopy(elements, index, elements, index + 1, Math.abs(index - size));
            size++;
            elements[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index
                    + ", Size: " + size);
        } else {
            return (T) elements[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index
                    + ", Size: " + size);
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index
                    + ", Size: " + size);
        } else {
            final Object element = elements[index];
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
            size--;
            return (T) element;
        }
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    for (int j = i; j < size; j++) {
                        elements[j] = elements[j + 1];
                    }
                    size--;
                    return element;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                for (int j = i; j < size; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Element not found " + element);
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
