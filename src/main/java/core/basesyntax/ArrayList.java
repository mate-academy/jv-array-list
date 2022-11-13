package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_NUMBER = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[MAX_NUMBER];
    }

    private void growArraySize() {
        if (size == elements.length) {
            T[] newArray = (T[]) new Object[size * 3 / 2 + 1];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
    }

    @Override
    public void add(T value) {
        growArraySize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        growArraySize();
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
        checkIndexOutOfBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBounds(index);
        elements[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        T value = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not found");
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
