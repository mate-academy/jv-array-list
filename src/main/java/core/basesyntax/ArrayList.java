package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 12;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
        elementData = (T[]) new Object[initialCapacity > DEFAULT_CAPACITY
                ? initialCapacity : DEFAULT_CAPACITY];
    }

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkForGrow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element with index " + index);
        }
        checkForGrow();
        System.arraycopy(elementData, index, elementData,
                 index + 1, elementData.length - 1 - index);
        size++;
        elementData[index] = value;
    }

    private void checkForGrow() {
        if (elementData.length - 1 <= size) {
            elementData = grow();
        }
    }

    private T[] grow() {
        return elementData = Arrays.copyOf(elementData,
                (int) (elementData.length * GROW_COEFFICIENT));
    }

    @Override
    public void addAll(List<T> list) {
        while (elementData.length < size + list.size()) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't find element with index " + index);
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData,
                 index, elementData.length - 1 - index);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (element != null && element.equals(elementData[i]))) {
                System.arraycopy(elementData, i + 1, elementData,
                         i, elementData.length - 1 - i);
                size--;
                break;
            }
            if (i == size - 1) {
                throw new NoSuchElementException("Can't remove element " + element);
            }
        }
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
}
