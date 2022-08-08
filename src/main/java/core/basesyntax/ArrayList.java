package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexInValidRange(index, size + 1);
        if (size == elements.length) {
            grow();
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
        isIndexInValidRange(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInValidRange(index, size);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInValidRange(index, size);
        Object removedObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elements[i]) || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Cannot remove element. "
                + "There is no such element.");
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
        Object[] newArray = new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void isIndexInValidRange(int index, int range) {
        if (index < 0 || index >= range) {
            throw new ArrayListIndexOutOfBoundsException("There is no element at that index"
                    + " or index is out of bound of list");
        }
    }
}
