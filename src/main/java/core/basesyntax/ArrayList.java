package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[START_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            growArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index
                    + " for size " + size);
        }
        if (size == elements.length) {
            growArray();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        size++;
        elements[index] = value;
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
        T removedElement = elements[index];
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == elements[i] || (element != null && (element.equals(elements[i])))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element " + element
                    + " doesn't exist in the List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void growArray() {
        T[] newList = (T[]) new Object[(int)(elements.length * INCREASING_COEFFICIENT)];
        System.arraycopy(elements, 0, newList, 0, elements.length);
        elements = newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index
                    + " for size " + size);
        }
    }
}
