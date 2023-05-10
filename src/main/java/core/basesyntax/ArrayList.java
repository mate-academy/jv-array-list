package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementsData;
    private int size;

    public ArrayList() {
        elementsData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkIfArrayFullAndGrow();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            checkIfArrayFullAndGrow();
            System.arraycopy(elementsData, index,
                    elementsData, index + 1, size - index);
            elementsData[index] = value;
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
        checkIndex(index);
        return elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        System.arraycopy(elementsData, index + 1,
                elementsData, index, size - 1 - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementsData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " absent in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIfArrayFullAndGrow() {
        if (elementsData.length == size) {
            T[] newElementsData =
                    (T[]) new Object[elementsData.length + (elementsData.length >> 1)];
            System.arraycopy(elementsData, 0, newElementsData, 0, elementsData.length);
            elementsData = newElementsData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + "out of bound for list length " + size);
        }
    }
}
