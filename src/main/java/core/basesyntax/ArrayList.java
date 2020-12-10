package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        this.elements = (T[]) new Object[ARRAY_CAPACITY];
    }

    public ArrayList(T[] elements, int size) {
        this.elements = elements;
        this.size = size;
    }

    @Override
    public void add(T value) {
        elements[size] = value;
        size++;
        if (elements.length == size) {
            grow();
        }
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (elements.length == size) {
                grow();
            }
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        exception(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        exception(index);
        elements[index] = value;
        return;
    }

    @Override
    public T remove(int index) {
        exception(index);
        T deletedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == elements[i]
                    || ((t != null && elements[i] != null) && t.equals(elements[i]))) {
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
        return size <= 0;
    }

    private void grow() {
        T[] newElements = (T[]) new Object[(int) (elements.length * 1.5)];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void exception(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
