package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (indexIsInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index passed");
        }
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        set(value, index);
        size++;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newSize = elementData.length + (elementData.length / 2);
            T[] newArray = (T[]) new Object[newSize];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
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
        if (indexIsInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index passed");
        }
        return elementData[index];
    }

    private boolean indexIsInvalid(int index) {
        return index < 0 || index >= size;
    }

    @Override
    public void set(T value, int index) {
        if (indexIsInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index passed");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (indexIsInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index passed");
        }
        T object = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return object;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEquals(element, elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    private boolean elementsAreEquals(T first, T second) {
        return (first == second) || (second != null && second.equals(first));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
}
