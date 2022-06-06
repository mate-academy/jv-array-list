package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_COEFFICIENT = 3 / 2;

    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index isn't valid.");
        }
        size++;
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        getAudit(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        getAudit(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        getAudit(index);
        final T removeObj = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removeObj;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element present");
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
        if (size >= DEFAULT_CAPACITY) {
            T[] newValues = (T[]) new Object[size * GROWTH_COEFFICIENT + 1];
            System.arraycopy(elements, 0, newValues, 0, size);
            elements = newValues;
        }
    }

    private void getAudit(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist.");
        }
    }
}
