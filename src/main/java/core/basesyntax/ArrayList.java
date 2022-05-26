package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        resize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkSize(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkSize(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkSize(index);
        T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (element == elements[i]) {
                    System.arraycopy(elements, i + 1, elements, i, size - i);
                    size--;
                    return null;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i]) || element == elements[i]) {
                T removed = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
                return removed;
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

    public void resize() {
        if (elements.length == size) {
            Object[] newArray = new Object[(int)(elements.length * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    public void checkSize(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }
}
