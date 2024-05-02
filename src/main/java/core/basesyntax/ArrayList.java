package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckForAdd(index);
        grow();
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
        indexCheck(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element");
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

    private void grow() {
        if (size >= elements.length) {
            Object[] newObject = new Object[(int) (elements.length * MULTIPLIER)];
            System.arraycopy(elements, 0, newObject, 0, elements.length);
            elements = (T[]) newObject;
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size());
        }
    }

    private void indexCheckForAdd(int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for length " + size());
        }
    }

    private int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null && elements[i] == null
                    || value != null && value.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }
}
