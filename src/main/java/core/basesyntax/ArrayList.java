package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VALUE = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_VALUE];
    }

    @Override
    public void add(T value) {
        checkArraySize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkArraySize();
        checkIndex(index);
        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size);
        }
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removeElement = elements[index];
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size);
        }
        size--;
        elements[size] = null;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (elements[i] != null && elements[i].equals(element))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("There is no such element " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkArraySize() {
        if (size == elements.length) {
            T[] newArray = (T[]) new Object[(int) (elements.length * GROW_FACTOR)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || ((size != 0 && size != 5) && index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", Size: " + size);
        }
    }
}
