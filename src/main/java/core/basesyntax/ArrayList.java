package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkGrow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAddRemove(index);
        checkGrow();
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
        checkIndexGetSet(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexGetSet(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexAddRemove(index);
        T removedItem = get(index);
        int length = size == elements.length ? size - index - 1 : size - index;
        System.arraycopy(elements, index + 1, elements, index, length);
        elements[--size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
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
        T[] newArray = (T[]) new Object[elements.length + (elements.length >> 1)];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkIndexAddRemove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }

    private void checkIndexGetSet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }

    private void checkGrow() {
        if (size == elements.length) {
            grow();
        }
    }
}
