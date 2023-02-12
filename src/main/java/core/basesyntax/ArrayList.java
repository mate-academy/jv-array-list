package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object [DEFAULT_CAPACITY];
    }

    private void increaseCapacity() {
        T[] old = elements;
        int capacity = old.length + (old.length >> 1);
        elements = (T[]) new Object[capacity];
        System.arraycopy(old,0, elements,0, size);
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element || (element != null && element.equals(elements[i])))) {
                return i;
            }
        }
        return -1;
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
        T[] old = elements;
        System.arraycopy(old, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int k = 0;
        while (list.size() + size() >= elements.length) {
            increaseCapacity();
        }
        for (int i = size(); i < size() + list.size();i++) {
            elements[i] = list.get(k++);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorect");
        }
        T removeItem = elements[index];
        for (int i = index; i < size() - 1; i++) {
            elements[i] = elements[i + 1];
        }
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
}
