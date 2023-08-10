package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (elements.length - size < list.size()) {
            grow();
        }
        T[] arrayOfIncomeElements = toArray(list);
        System.arraycopy(arrayOfIncomeElements, 0, elements, size, arrayOfIncomeElements.length);
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkGetIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkGetIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkGetIndex(index);
        T oldElementValue = elements[index];
        if (size - 1 > index) {
            System.arraycopy(elements, index + 1, elements, index, size - 1);
        } else {
            elements[index] = null;
        }
        size--;
        return oldElementValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || (elements[i] == element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(String.format("No %s in array list", element.toString()));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] toArray(List<T> list) {
        T[] resulArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resulArray[i] = list.get(i);
        }
        return resulArray;
    }

    private void grow() {
        int newLength = (int) (elements.length * GROWTH_COEFFICIENT);
        T[] tempArray = (T[]) new Object[newLength];
        System.arraycopy(elements, 0, tempArray, 0, size);
        elements = tempArray;
    }

    private void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    private void checkGetIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
