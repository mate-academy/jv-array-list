package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int INITIAL_CAPACITY = 10;
    private int capacity;
    private int size;
    private Object[] elements;

    ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        size = 0;
    }

    private int getIndex(T value) {
        int index = -1;
        int counter = 0;
        for (Object o : elements) {
            if (o != null && o.equals(value) || o == value) {
                index = counter;
                return index;
            }
            counter++;
        }
        return index;
    }

    private void resize() {
        Object[] temp = new Object[size + (size >> 1)];
        System.arraycopy(elements, 0, temp, 0, elements.length);
        elements = temp;
        capacity = size + (size >> 1);
    }

    @Override
    public void add(T value) {
        if (size() == capacity) {
            resize();
        }
        if (isEmpty()) {
            elements[0] = value;
            size++;
        } else {
            elements[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds of the list size");
        }
        if (size == capacity) {
            resize();
        }
        size++;
        for (int i = size - 1; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds of the list size");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds of the list size");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bounds of the list size");
        }
        T removedelement = (T) elements[index];
        if (index == size - 1) {
            size--;
            return removedelement;
        }
        System.arraycopy(elements, index + 1, elements, index,size - index);
        size--;
        return removedelement;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == - 1) {
            throw new NoSuchElementException();
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
