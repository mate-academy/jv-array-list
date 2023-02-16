package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INCRICE = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on position " + index);
        }
        resize();
        System.arraycopy(elements, index, elements, index + 1,size - index);
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
        checkCapacity(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkCapacity(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkCapacity(index);
        final T temp = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element)
                    || ((elements[i] == element)
                    || (elements[i] != null && elements[i].equals(element)))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't found element by value");
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
            int capacity = (int) (elements.length * CAPACITY_INCRICE);
            T[] tempArray = (T[]) new Object[capacity];
            System.arraycopy(elements, 0, tempArray, 0, size);
            elements = tempArray;
        }
    }

    private void checkCapacity(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
