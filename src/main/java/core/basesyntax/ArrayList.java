package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAYCOPY_POSITION = 0;
    private static final int ARRAYCOPY_MOVE_POSITION = 1;
    private static final double GROW_SIZE = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (isFull()) {
            grow();
        }
        System.arraycopy(elements, index, elements,
                index + ARRAYCOPY_MOVE_POSITION, size - index);
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
        Object removedElement = elements[index];
        System.arraycopy(elements,index + ARRAYCOPY_MOVE_POSITION,
                elements,index,elements.length - index - ARRAYCOPY_MOVE_POSITION);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Array list doesn't contain element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private void grow() {
        double newSize = size * GROW_SIZE;
        Object[] tempElements = new Object[(int) newSize];
        System.arraycopy(elements, ARRAYCOPY_POSITION, tempElements,
                ARRAYCOPY_POSITION, elements.length);
        elements = tempElements;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is out of size: " + size);
        }
    }
}
