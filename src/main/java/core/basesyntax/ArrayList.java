package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double ADD_CAPACITY = 1.5;
    private static final int POSITION_ZERO = 0;
    private T [] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growList();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growList();
        insertElement(value,index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForExistingElements(index);
        final T returnedElement = elements[index];
        return returnedElement;
    }

    @Override
    public void set(T value, int index) {
        checkIndexForExistingElements(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForExistingElements(index);
        final T removedElement = elements[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

    private void checkIndexForExistingElements(int index) {
        if (!(index >= 0 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index
                    + " is out of bounds. "
                    + "Size: "
                    + size);
        }
    }

    private void checkIndexForAddition(int index) {
        if (!(index >= 0 && index <= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index
                    + " is out of bounds. "
                    + "Size: "
                    + size);
        }
    }

    private void growList() {
        if (size == elements.length) {
            Object [] extendedArray = new Object[(int) (elements.length * ADD_CAPACITY)];
            System.arraycopy(elements,
                    POSITION_ZERO,
                    extendedArray,
                    POSITION_ZERO,
                    elements.length);
            elements = (T[]) extendedArray;
        }
    }

    private void insertElement(T value, int index) {
        checkIndexForAddition(index);
        System.arraycopy(elements,
                index, elements,
                index + 1,
                elements.length - index - 1);
        elements[index] = value;
        size++;
    }

    private void removeElement(int index) {
        System.arraycopy(elements,
                index + 1,
                elements, index,
                elements.length - index - 1);
        size--;
    }
}

