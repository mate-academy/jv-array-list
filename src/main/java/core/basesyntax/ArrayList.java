package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_ARRAY_CAPACITY = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
        if (size == elements.length) {
            resize();
        }
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
        isIndexInRange(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInRange(index);
        T removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(elements[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such an element is missing: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isIndexInRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
        return true;
    }

    private void resize() {
        int newCapacity = (int) (elements.length * INCREASE_ARRAY_CAPACITY);
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private boolean isEquals(T firstElement, T secondElement) {
        return (firstElement == secondElement
                || firstElement != null
                && firstElement.equals(secondElement));
    }
}
