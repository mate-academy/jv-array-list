package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;

    private Object[] elements;

    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        checkCapacity();
        System.arraycopy(elements, index, elements,
                index + 1, elements.length - 2);
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
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object removedElement = elements[index];
        System.arraycopy(elements, index + 1,
                elements, index, elements.length - 2);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == t) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size() == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }
}
