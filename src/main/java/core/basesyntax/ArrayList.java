package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;

    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        resize();
        System.arraycopy(elements, index, elements,index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            elements[size] = list.get(i);
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIfIndexFits(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexFits(index);
        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIfIndexFits(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (findIndex(element) == -1) {
            throw new NoSuchElementException("There is no such element in the list, " + element);
        }
        return remove(findIndex(element));
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
            int newCapacity = (int) (elements.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, " + index);
        }
    }

    private void checkIfIndexFits(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index in the list, "
                    + index);
        }
    }

    private int findIndex(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || elements[i] != null && elements[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }
}
