package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int CAPACITY = 10;
    private int elementsCount;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        ArrayList.CAPACITY = capacity;
        elements = (T[]) new Object[CAPACITY];
    }

    private boolean isFull() {
        return elementsCount == elements.length;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        if (isFull()) {
            int len = elements.length + (elements.length >> 1);
            Object[] newElements = new Object[len];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = (T[]) newElements;
        }
    }

    @SuppressWarnings("unchecked")
    private void grow(int listSize) {
        int len = listSize + elements.length + (elements.length >> 1);
        Object[] newElements = new Object[len];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        this.elements = (T[]) newElements;
    }

    @Override
    public void add(T value) {
        grow();
        elements[elementsCount++] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(T value, int index) {
        validateIndex(index, 1);
        if (index == elementsCount) {
            add(value);
            return;
        }
        grow();
        Object[] newElements = new Object[elements.length];
        System.arraycopy(elements, 0, newElements, 0, index);
        newElements[index] = value;
        elementsCount++;
        System.arraycopy(
                elements,
                index,
                newElements,
                index + 1,
                elements.length - index - 1
        );
        this.elements = (T[]) newElements;
    }

    @Override
    public void addAll(List<T> list) {
        if (elements.length - elementsCount < list.size()) {
            grow(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elements[elementsCount++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index, 0);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index, 0);
        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        final T t = get(index);
        Object[] newElements = new Object[elements.length];
        System.arraycopy(elements, 0, newElements, 0, index);
        System.arraycopy(
                elements,
                index + 1,
                newElements,
                index,
                elements.length - index - 1
        );
        elementsCount--;
        this.elements = (T[]) newElements;
        return t;
    }

    @Override
    public T remove(T element) {
        T last = getLast();
        if (last != null && last.equals(element)) {
            return remove(elementsCount - 1);
        }
        for (int i = 0; i < elementsCount - 1; i++) {
            if (element == elements[i] || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("element does not exist");
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    private T getLast() {
        return elements[elementsCount - 1];
    }

    private void validateIndex(int index, int shift) {
        if (index < 0 || index >= elementsCount + shift) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + " is not valid");
        }
    }
}
