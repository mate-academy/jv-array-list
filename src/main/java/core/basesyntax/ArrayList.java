package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int capacity = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        growIfNeed();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growIfNeed();
        checkAddIndex(index);
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
        int updateSize = size - 1;
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, updateSize - index);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        return remove(getIndexByElement(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeed() {
        if (size == elements.length) {
            grow();
        }
    }

    private int getIndexByElement(T element) {
        int index = Arrays.asList(elements).indexOf(element);
        if (index != -1) {
            return index;
        }
        throw new NoSuchElementException("Can't remove element " + element);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String.format("Out of bound exception."
                    + " %s is not included in the length %s", index, size));
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Out of bound exception."
                            + " %s is not included in the length %s", index, size));
        }
    }

    private void grow() {
        int capacity = (int) (elements.length * RESIZE_FACTOR);
        T[] copy = elements;
        elements = (T[]) new Object[capacity];
        System.arraycopy(copy, 0, elements, 0, size);
    }
}
