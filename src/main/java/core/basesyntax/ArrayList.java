package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T element) {
        if (size == elements.length) {
            resizeArray();
        }
        elements[size++] = element;
    }

    @Override
    public void add(T element, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Некоректний індекс: " + index);
        }
        if (size == elements.length) {
            resizeArray();
        }
        System.arraycopy(
                elements, index,
                elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Некоректний індекс: " + index);
        }
        T removedElement = getElement(index);
        System.arraycopy(
                elements, index + 1,
                elements, index,
                size - index - 1);
        elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                if (element == null) {
                    return remove(i);
                }
            } else if (elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void set(T element, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Некоректний індекс: " + index);
        }
        elements[index] = element;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Некоректний індекс: " + index);
        }
        return getElement(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        int newCapacity = (int) (elements.length * GROWTH_FACTOR);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @SuppressWarnings("unchecked")
    private T getElement(int index) {
        return (T) elements[index];
    }
}
