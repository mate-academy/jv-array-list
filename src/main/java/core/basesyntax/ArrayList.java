package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int INITIAL_SIZE = 10;
    static final double k = 1.5;
    private T[] elements;
    static int size;

    public ArrayList() {
        elements = (T[]) new Object[INITIAL_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        resizeIfNeed();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is invalid for size " + size);
        }
        resizeIfNeed();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        isInArray(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isInArray(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isInArray(index);
        T removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    private void resizeIfNeed() {
        if (size == elements.length) {
            resize();
        }
    }

    private void resize() {
        int newSize = (int) (size * k);
        T[] elements2 = (T[]) new Object[newSize];
        System.arraycopy(elements, 0, elements2, 0, elements.length);
        elements = elements2;
    }

    private void isInArray(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
