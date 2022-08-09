package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length - 1) {
            resize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkRangeForAdd(index);
        if (size == elements.length - 1) {
            resize();
        }
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
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (element == null ? elements[i] == element : element.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Elements not found" + element);
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
        Object[] newArray = new Object[elements.length + elements.length / 2];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound " + index);
        }
    }

    private void checkIndex(int index) {
        if (isEmpty() || index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found");
        }
    }
}
