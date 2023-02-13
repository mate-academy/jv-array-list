package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object [DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            increaseCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        if (elements.length == size) {
            increaseCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        while (list.size() + size >= elements.length) {
            increaseCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        validationOfIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        validationOfIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validationOfIndex(index);
        T removeItem = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeItem;
    }

    @Override
    public T remove(T element) {
        if (getIndex(element) == -1) {
            throw new NoSuchElementException("Element doesnt exist");
        }
        return remove(getIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    private void increaseCapacity() {
        T[] old = elements;
        int capacity = old.length + (old.length >> 1);
        elements = (T[]) new Object[capacity];
        System.arraycopy(old, 0, elements, 0, size);
    }

    private void validationOfIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element || (element != null && element.equals(elements[i])))) {
                return i;
            }
        }
        return -1;
    }
}
