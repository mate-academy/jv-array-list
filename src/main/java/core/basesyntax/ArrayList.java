package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is not valid for size "
                    + size + ".");
        }
        checkSize();
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
        Object deletedElement = elements[index];
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return (T)deletedElement;
    }

    @Override
    public T remove(T deletedElement) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && deletedElement == null)
                    || deletedElement != null && Objects.equals(deletedElement, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element.");
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
        Object[] oldElements = elements;
        elements = new Object[(int) (size * GROWTH_COEFFICIENT)]; ////////////////////////////
        System.arraycopy(oldElements, 0, elements, 0, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is not valid for size " + size + ".");
        }
    }

    private void checkSize() {
        if (size == elements.length) {
            grow();
        }
    }
}
