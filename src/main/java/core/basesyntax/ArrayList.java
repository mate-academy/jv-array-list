package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Double AUGMENTER_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        checkCapacity();
        System.arraycopy(elements, index, elements, index + 1, (size - index));
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
        checkIndex(index, size);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        elements[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || (elements[i] != null) && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no " + element + " Element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + ", Out Of Bounds");
        }
    }

    private void checkCapacity() {
        if (elements.length == size) {
            resizeArray();
        }
    }

    private void resizeArray() {
        T[] extendedArray = (T[]) new Object[(int) (elements.length * AUGMENTER_FACTOR)];
        System.arraycopy(elements, 0, extendedArray, 0, size);
        elements = extendedArray;
    }
}
