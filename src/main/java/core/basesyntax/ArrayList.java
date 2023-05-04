package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            indexCheck(index);
            resize();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
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
        indexCheck(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T element = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
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
        return size == 0;
    }

    private void resize() {
        if (size == elements.length) {
            T[] buffer = (T[]) new Object[elements.length + elements.length / 2];
            System.arraycopy(elements, 0, buffer, 0, size);
            elements = buffer;
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The passed index " + index + " is wrong");
        }
    }
}
