package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        if (size + 1 > elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("List out of bounds");
        }
        if (index == size) {
            add(value);
            return;
        }
        if (size + 1 > elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > elements.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T temp = (T) elements[index];
        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size] = null;
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element == null) {
                remove(i);
                return element;
            } else if (elements[i] == null) {
                continue;
            }
            if (elements[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] tempArray = (T[]) new Object[(int) (elements.length * 1.5)];
        System.arraycopy(elements, 0, tempArray, 0, elements.length);
        elements = tempArray;
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("List out of bounds");
        }
    }
}
