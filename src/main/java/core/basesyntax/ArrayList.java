package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] elementArray;
    private int size;

    public ArrayList() {
        elementArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementArray.length) {
            grow();
        }
        elementArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (size == this.elementArray.length) {
                grow();
            }
            System.arraycopy(elementArray, index, elementArray, index + 1, size - index);
            elementArray[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) throws ArrayListIndexOutOfBoundsException {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = get(index);
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        int index = findIndexElement(element);
        T result = elementArray[index];
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index - 1);
        size--;
        return result;
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
        Object[] defaultNewCapacity = new Object[(int) (elementArray.length * GROW_COEFFICIENT)];
        System.arraycopy(elementArray, 0, defaultNewCapacity, 0, elementArray.length);
        elementArray = (T[]) defaultNewCapacity;
    }

    private int findIndexElement(T element) {
        for (int i = 0; i < size; i++) {
            if (elementArray[i] == element || elementArray[i] != null
                    && elementArray[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("There is no element with value " + element
                + " in ArrayList");
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
        }
    }
}
