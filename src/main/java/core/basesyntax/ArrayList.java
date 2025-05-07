package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double UP_SIZE_CAPACITY = 1.5;
    private T[] elements = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        resize();
        checkIndex(index);
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
        checkIndex(index + 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T removeElement = elements[index];
        int numberOfElements = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numberOfElements);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This array list sheet does not contain an element "
                + element);
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
        if (size >= elements.length) {
            int resize = (int) (elements.length + (elements.length * UP_SIZE_CAPACITY));
            T[] resizeElement = (T[]) new Object[resize];
            System.arraycopy(elements, 0, resizeElement, 0, elements.length);
            elements = resizeElement;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
