package core.basesyntax;

import core.basesyntax.util.ObjectUtil;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int capacity = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private int size;
    private T[] elements = (T[]) new Object[capacity];

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddAtIndex(index);
        checkCapacity();
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
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findElement(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (capacity == size) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        capacity *= RESIZE_FACTOR;
        T[] newElements = elements;
        elements = (T[]) new Object[capacity];
        System.arraycopy(newElements, 0, elements, 0, size);

    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void checkAddAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private int findElement(T element) {
        for (int i = 0; i < size; i++) {
            if (ObjectUtil.equals(elements[i], element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }
}
