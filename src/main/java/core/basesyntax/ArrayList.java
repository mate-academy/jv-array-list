package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int CAPACITY_EMPTY_ELEMENT_DATA = 0;
    public static final int DEFAULT_CAPACITY_ELEMENT_DATA = 10;
    public static final double CAPACITY_GROW_FACTOR = 1.5;
    private int size = 0;

    @SuppressWarnings("unchecked")
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY_ELEMENT_DATA];

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = grow(elementData);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (size >= elementData.length) {
            elementData = grow(elementData);
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private T[] grow(T[] elementData) {
        int newCapacity = (int) (elementData.length * CAPACITY_GROW_FACTOR);
        return Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
        final T removedElement = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null) {
                if (element == null) {
                    return remove(i);
                }
            } else if (elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list is exist: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == CAPACITY_EMPTY_ELEMENT_DATA;
    }
}
