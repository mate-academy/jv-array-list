package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        growIfArrayFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfArrayFull();
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
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
        T removeElement = elements[index];
        for (int i = index; i < elements.length - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        T deletedElement;
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null
                    && elements[i].equals(element))
                    || elements[i] == element) {
                deletedElement = elements[i];
                remove(i);
                return deletedElement;
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

    public void growIfArrayFull() {
        if (size >= elements.length) {
            int oldSize = elements.length;
            int newSize = oldSize + (oldSize >> 1);
            elements = Arrays.copyOf(elements, newSize);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("wrong index: " + index
                    + " for ArrayList size: " + size);
        }
    }
}
