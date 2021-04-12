package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index,size);
        if (size == elements.length) {
            elements = grow();
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
        checkIndex(index,size - 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,size - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,size);
        int numMoved = size - index - 1;
        T oldValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (elements[i] == element || element != null && element.equals(elements[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int resize() {
        return (int) (size * 1.5);
    }

    private void checkIndex(int index,int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private int newSize() {
        return (size * 3) / 2;
    }

    private T[] grow() {
        T[] growArray = (T[]) new Object[newSize()];
        System.arraycopy(elements, 0, growArray, 0, size);
        return growArray;
    }
}
