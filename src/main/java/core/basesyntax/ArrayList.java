package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_AL_SIZE = 10;
    private static final double CAPACITY_GROWTH_VALUE = 1.5;
    private static final int ZERO_NUMBER = 0;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_AL_SIZE];
        size = ZERO_NUMBER;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value by this index");
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
            add(array[i]);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't get value by this index");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't set value by this index");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove value by this index");
        }
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
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
        return (size == ZERO_NUMBER) ? true : false;
    }

    public void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * CAPACITY_GROWTH_VALUE);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, ZERO_NUMBER, newArray, ZERO_NUMBER, elements.length);
            elements = newArray;
        }
    }
}
