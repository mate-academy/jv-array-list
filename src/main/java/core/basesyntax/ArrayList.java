package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private int currentCapacity = INITIAL_CAPACITY;
    private Object[] array = new Object[INITIAL_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        checkCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexIsValid(index, size + 1);
        checkCapacity();
        System.arraycopy(array, index,
                array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        int minCapacity = size + listSize;
        if (minCapacity > currentCapacity) {
            grow(minCapacity);
        }
        for (int i = 0; i < listSize; i++) {
            int index = size + i;
            array[index] = list.get(i);
        }
        size += listSize;
    }

    @Override
    public T get(int index) {
        checkIfIndexIsValid(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexIsValid(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexIsValid(index);
        size--;
        Object element = array[index];
        if (size > index) {
            System.arraycopy(array, index + 1,
                    array, index, size - index);
        }
        array[size] = null;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size == currentCapacity) {
            grow();
        }
    }

    private void grow() {
        currentCapacity *= GROWTH_FACTOR;
        grow(currentCapacity);
    }

    private void grow(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0,
                newArray, 0, size);
        array = newArray;
    }

    private void checkIfIndexIsValid(int index) {
        checkIfIndexIsValid(index, size);
    }

    private void checkIfIndexIsValid(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index");
        }
    }
}
