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
            elements = grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!(isIndexInValidRange(index, size + 1))) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add element. "
                    + "Index out of bounds of this list.");
        }
        if (size == elements.length) {
            elements = grow();
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
        if (!(isIndexInValidRange(index, size))) {
            throw new ArrayListIndexOutOfBoundsException("Cannot get element. "
                    + "There is no element at that index.");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (!(isIndexInValidRange(index, size))) {
            throw new ArrayListIndexOutOfBoundsException("Cannot set element. "
                    + "There is no element at that index.");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!(isIndexInValidRange(index, size))) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element. "
                    + "There is no element at that index.");
        }
        Object removedObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        Object removedObject = null;
        for (int i = 0; i < size; i++) {
            if ((element == elements[i]) || (element != null && element.equals(elements[i]))) {
                removedObject = elements[i];
                remove(i);
                return (T) removedObject;
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

    private Object[] grow() {
        Object[] grow = new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, grow, 0, size);
        return grow;
    }

    private boolean isIndexInValidRange(int index, int range) {
        return index >= 0 && index < range;
    }
}
