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
        resize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexOutOfBounds(index, size);
        resize();
        if (size - index >= 0) {
            System.arraycopy(elements, index, elements, index + 1,
                    size - index);
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
        verifyIndexOutOfBounds(index, size - 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndexOutOfBounds(index, size - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndexOutOfBounds(index, size - 1);
        final T deletedValue = elements[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(elements, index + 1, elements,
                    index, size - 1 - index);
        }
        elements[size - 1] = null;
        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elements[i]) || element == elements[i]) {
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

    private void resize() {
        if (size == elements.length) {
            T[] newElements = (T[]) new Object[(int)(elements.length * 1.5)];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void verifyIndexOutOfBounds(int index, int lastAllowedIndex)
            throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > lastAllowedIndex) {
            throw new ArrayListIndexOutOfBoundsException("index Out Of Bounds");
        }
    }
}
