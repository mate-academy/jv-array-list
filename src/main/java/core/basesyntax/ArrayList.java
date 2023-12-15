package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double GROWTH_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growCapacityIfArrayFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAddition(index);
        if (size + 1 >= elements.length) {
            growCapacity();
        }
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
        validateIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = value;

    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = (T) elements[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                removeElement(i);
                return null;
            }
            if (elements[i] != null && elements[i].equals(element)) {
                T removedElement = (T) elements[i];
                removeElement(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacity() {
        int newGrowCapacity = (int) (elements.length * GROWTH_MULTIPLIER);
        T[] newElements = (T[]) new Object[newGrowCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private void growCapacityIfArrayFull() {
        if (size == elements.length) {
            growCapacity();
        }
    }

    public void removeElement(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    private void validateIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cannot find index : "
                    + index + " because size: " + size);
        }
    }

    private void validateIndexForAddition(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cannot find index : "
                    + index + " because size: " + size);
        }
    }
}
