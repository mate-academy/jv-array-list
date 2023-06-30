package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
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
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
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

    private void ensureCapacity() {
        if (size == elements.length) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        Object[] newElements = new Object[(int) (elements.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throwIndexOutOfBounds(index);
        }
    }

    private void throwIndexOutOfBounds(int index) {
        throw new ArrayListIndexOutOfBoundsException(String.format(
                "Index '%d' out of bounds for size '%d'", index, elements.length));
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throwIndexOutOfBounds(index);
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }
}
