package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_COEFFICIENT = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizing();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index " + index + " passed to the method is invalid");
        }
        resizing();
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
        indexValidation(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T value = (T) elements[index];
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element " + element + " present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidation(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index " + index + " passed to the method is invalid");
        }
    }

    private void resizing() {
        if (size >= elements.length) {
            Object[] arrayNew = new Object[(int) (elements.length * GROWTH_COEFFICIENT)];
            System.arraycopy(elements, 0, arrayNew, 0, elements.length);
            elements = arrayNew;
        }
    }
}
