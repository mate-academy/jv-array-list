package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        resizeIfNeeded();
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
        checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removedElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement;
        int index = findElement(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element was found");
        } else {
            removedElement = get(index);
            System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
            size--;
        }
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds: ");
        }
    }

    private void resizeIfNeeded() {
        if (size == elements.length) {
            Object[] newArray = new Object[(int) (elements.length + elements.length * LOAD_FACTOR)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private int findElement(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }
}
