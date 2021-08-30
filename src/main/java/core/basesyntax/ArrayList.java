package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 12;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
        elementData = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element with index " + index);
        }
        grow();
        System.arraycopy(elementData, index, elementData,
                 index + 1, elementData.length - 1 - index);
        size++;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i), size);
        }
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
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == elementData.length - 1) {
            T[] grownElementData = (T[]) new Object[(int) (elementData.length * GROW_COEFFICIENT)];
            System.arraycopy(elementData, 0, grownElementData,
                    0, elementData.length - 1);
            elementData = grownElementData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't find element with index " + index);
        }
    }
}
