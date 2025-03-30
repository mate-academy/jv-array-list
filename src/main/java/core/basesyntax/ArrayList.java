package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MULTIPLIER = 1.5;
    private static final int DEFAULT_SIZE = 10;
    private static final int ZERO = 0;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
        size = ZERO;
    }

    public void checkCapacity() {
        if (size == elements.length) {
            grow();
        }
    }

    public void checkIndex(int index) {
        if (index < ZERO || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index.");
        }
    }

    public void checkIndexForAdd(int index) {
        if (index < ZERO || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index.");
        }
    }

    public void grow() {
        int newSize = (int) (size * MULTIPLIER);
        Object[] newElements = new Object[newSize];
        System.arraycopy(elements, ZERO, newElements, ZERO, size);
        elements = newElements;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkCapacity();
        checkIndexForAdd(index);
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
        return castElement(elements[index]);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedElement = castElement(elements[index]);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(elements[i]))
                    || (element == null && elements[i] == null)) {
                T removedElement = castElement(elements[i]);
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Such element doesn't exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T castElement(Object element) {
        return (T) element;
    }
}
