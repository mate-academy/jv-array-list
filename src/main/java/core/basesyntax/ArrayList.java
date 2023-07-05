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
        checkAddIndex(index);
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
        final T removeElement = elements[index];
        int newSize = size - 1;
        System.arraycopy(elements, index + 1, elements, index, newSize - index);
        elements[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        remove(findIndex(element));
        return element;
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
        if (size == capacity) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        capacity *= RESIZE_FACTOR;
        T[] tmp = elements;
        elements = (T[]) new Object[capacity];
        System.arraycopy(tmp, 0, elements, 0, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bounds", index));
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %s out of bounds", index));
        }
    }

    private int findIndex(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (ObjectUtil.equals(elements[i], element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't remove element " + element);
    }
}
