package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROWTH_INDEX = 1.5;
    private static final int ONE = 1;
    private int size = 0;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateAddIndex(index);
        ensureCapacity();

        if (index < size) {
            T[] newArr = (T[]) new Object[elements.length];
            System.arraycopy(elements, 0, newArr, 0, index);
            newArr[index] = value;
            System.arraycopy(elements, index, newArr, index + ONE, size - index);
            elements = newArr;
            size++;
        }

        if (index == size) {
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
        validateIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T[] newArr = (T[]) new Object[elements.length];
        System.arraycopy(elements, 0, newArr, 0, index);
        final T removedValue = elements[index];
        System.arraycopy(elements, index + 1, newArr, index, size - index - 1);
        elements = newArr;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (isElementPresent(element, i)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
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
            elements = grow();
        }
    }

    private T[] grow() {
        int newLength = (int) (elements.length * GROWTH_INDEX);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        return newArray;
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Validation falls with index: %d for size: %d", index, size));

        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Validation falls with index: %d for size: %d", index, size));

        }
    }

    private boolean isElementPresent(T element, int i) {
        if ((elements[i] == null && element == null)
                || (elements[i] != null && element != null && elements[i].equals(element))) {
            return true;
        }
        return false;
    }
}
