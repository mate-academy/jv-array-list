package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        validateSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        validateSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndexForAddition(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndexForAddition(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndexForAddition(index);
        T elementToRemove = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " was not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void handleSize() {
        Object[] tempArray = new Object[(int) (array.length * INCREASE_RATE)];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }

    private void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index
                    + "is out of bounds for size " + size);
        }
    }

    private void validateIndexForAddition(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds of the list size");
        }
    }

    private void validateSize() {
        if (size == array.length) {
            handleSize();
        }
    }
}