package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_SCALE_FACTOR = 1.5;
    private int size;
    private Object[] elements;

    public ArrayList(int capacity) {
        this.elements = new Object[capacity];
    }

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseArraySizeIfFilled();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ifIndexNotOutOfBonds(index, size());
        increaseArraySizeIfFilled();
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
        ifIndexNotOutOfBonds(index, size() - 1);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        ifIndexNotOutOfBonds(index, size() - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        ifIndexNotOutOfBonds(index, size() - 1);
        T removedElement = (T) elements[index];
        if (index < size() - 1) {
            System.arraycopy(elements, index + 1, elements, index, size() - index);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No element " + element.toString() + " in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArraySizeIfFilled() {
        if (size() == elements.length) {
            Object[] increasedArray = new Object[(int) (size * ARRAY_SCALE_FACTOR + 1)];
            System.arraycopy(elements, 0, increasedArray, 0, size);
            elements = increasedArray;
        }
    }

    private void ifIndexNotOutOfBonds(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of acceptable range");
        }
    }
}
