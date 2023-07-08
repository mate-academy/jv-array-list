package core.basesyntax;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final double GROW_FACTOR = 1.5;

    private T[] elements;
    private int size;

    @Override
    public void add(T value) {
        addCellForNewElement(value);
        elements[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        int prevSize = size;
        addCellForNewElement(value);
        validateIndex(index);
        if (index < prevSize) {
            System.arraycopy(elements, index, elements, index + 1, prevSize - index);
        }
        elements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Input list is null");
        }
        if (list.size() == 0) {
            return;
        }
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
        final T value = elements[index];
        size--;
        if (index < size) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        elements[size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element)
                    || (elements[i] != null && elements[i].equals(element))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element in the list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void addCellForNewElement(T value) {
        if (elements == null) {
            elements = createArray(value, INITIAL_SIZE);
        }
        size++;
        if (size > elements.length) {
            T[] newArray = createArray(value, (int) (elements.length * GROW_FACTOR));
            System.arraycopy(elements, 0, newArray, 0, elements.length);
            elements = newArray;
        }
    }

    private T[] createArray(T value, int arraySize) {
        if (value == null && elements != null && elements.length > 0) {
            value = elements[0];
        }
        return (T[]) Array.newInstance((value != null)
                            ? value.getClass()
                            : Object.class, arraySize);
    }

    private void validateIndex(int index) {
        if (elements == null) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: "
                    + index + "; array has not been initialized");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: "
                    + index + "; index greater than array size; array size: " + size);
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: "
                    + index + "; index should be positive; array size: " + size);
        }
    }
}
