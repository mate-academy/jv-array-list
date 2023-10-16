package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private static final String NO_SUCH_ELEMENT_MESSAGE = "Element not found: ";
    private static final String INDEX_OUT_OF_BOUND_MESSAGE = "Index out of bound: ";
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        growIfNeeded();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growIfNeeded();
        if (index != size) {
            checkIndex(index);
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfNeeded();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUND_MESSAGE + index);
        }
    }

    private void growIfNeeded() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * GROWTH_FACTOR);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
}
