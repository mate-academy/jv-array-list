package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double INCREMENT_CAPACITY = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkArrayBoundary(index);
        checkFillingArray();
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
        checkArrayBoundary(index + 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkArrayBoundary(index + 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkArrayBoundary(index + 1);
        T removeObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elements[i])
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element isn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkFillingArray() {
        if (size == elements.length) {
            T[] tempArray = (T[]) new Object[(int) (elements.length * INCREMENT_CAPACITY)];
            System.arraycopy(elements, 0, tempArray, 0, size);
            elements = tempArray;
        }
    }

    private void checkArrayBoundary(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
