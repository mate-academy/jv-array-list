package core.basesyntax;

import core.basesyntax.util.ObjectUtil;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private int capacity = INITIAL_CAPACITY;
    private int size;
    private T[] elements = (T[]) new Object[INITIAL_CAPACITY];

    @Override
    public void add(T value) {
        changeSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        changeSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            changeSize();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkRemoveIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkRemoveIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRemoveIndex(index);
        final T removeElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        T removeElement = null;
        for (int i = 0; i < elements.length; i++) {
            if (ObjectUtil.equals(elements[i], element)) {
                removeElement = remove(i);
            }
        }
        if (removeElement == null) {
            throw new NoSuchElementException("Can't find element to remove ");
        } else {
            return removeElement;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void changeSize() {
        if (size == capacity) {
            capacity *= RESIZE_FACTOR;
            T[] tmp = elements;
            elements = (T[]) new Object[capacity];
            System.arraycopy(tmp, 0, elements, 0, size);
        }
    }

    private void checkRemoveIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't access element at index " + index);
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't access element at index " + index);
        }
    }
}

