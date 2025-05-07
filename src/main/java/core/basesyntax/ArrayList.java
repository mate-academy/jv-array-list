package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int INDEX_OF_FIRST_ELEMENT = 0;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || checkIndex(index)) {
            if (size >= elements.length) {
                grow();
            }
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return elements[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            T result = elements[index];
            removeElementByIndex(index);
            size--;
            return result;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null
                    && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in the list: " + element);
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
        T[] temporaryCopy = elements;
        elements = (T[]) new Object[(int) (elements.length * GROWTH_COEFFICIENT)];
        System.arraycopy(temporaryCopy, INDEX_OF_FIRST_ELEMENT, elements, INDEX_OF_FIRST_ELEMENT,
                temporaryCopy.length);
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid" + index);
        }
        return index < size;
    }

    private void removeElementByIndex(int index) {
        if (index == elements.length - 1) {
            elements[index] = null;
            return;
        }
        System.arraycopy(elements, index + 1, elements, index,
                    size - index);
    }
}
