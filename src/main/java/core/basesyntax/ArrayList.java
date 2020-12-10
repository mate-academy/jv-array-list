package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private boolean isInvalidIndex(int index) {
        return (index >= size || index < 0);
    }

    private void resize() {
        T[] resizedArray = (T[]) new Object[(elements.length + (elements.length >> 1))];
        System.arraycopy(elements, 0, resizedArray, 0, size);
        elements = resizedArray;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index + " is out of bounds");
        }
        if (size == elements.length) {
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
        if (isInvalidIndex(index)) {
            throw new ArrayIndexOutOfBoundsException(index + " is out of bounds");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (isInvalidIndex(index)) {
            throw new ArrayIndexOutOfBoundsException(index + " is out of bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isInvalidIndex(index)) {
            throw new ArrayIndexOutOfBoundsException(index + " is out of bounds");
        }
        T target = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return target;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size(); i++) {
            if (elements[i] == t || elements[i] != null && elements[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(t + " element is not in the List");
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
