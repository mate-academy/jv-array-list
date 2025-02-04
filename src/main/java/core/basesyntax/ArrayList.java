package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        int newCapacity = (int) (elements.length * 1.5);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void growTo(int minCapacity) {
        int newCapacity = elements.length;
        while (newCapacity < minCapacity) {
            newCapacity = (int) (newCapacity * 1.5);
        }
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        if (size == elements.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        int reCapacity = size + list.size();

        if (reCapacity > elements.length) {
            growTo(reCapacity);
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        if (elements[index] != null) {
            return elements[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
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
}
