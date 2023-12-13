package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final int ONE_INDEX = 1;
    private static final double GROW_FACTOR = 1.5;
    private T[] array;

    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForNewArray(index);
        if (size == array.length) {
            grow();
        }
        if (index != size) {
            System.arraycopy(array, index, array, index + ONE_INDEX, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndexForOldValue(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndexForOldValue(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndexForOldValue(index);
        T valueToRemove = array[index];
        int numMoved = size - index - ONE_INDEX;
        if (numMoved > 0) {
            System.arraycopy(array, index + ONE_INDEX, array, index, numMoved);
        }
        array[--size] = null; // Clear to let GC do its work
        return valueToRemove;
    }

    @Override
    public T remove(T element) {
        int indexFind = -1;
        for (int i = 0; i < size; i++) {
            if (element == null && array[i] == null) {
                indexFind = i;
                break;
            } else if (element != null && array[i] != null && array[i].equals(element)) {
                indexFind = i;
                break;
            }
        }
        if (indexFind == -1) {
            throw new NoSuchElementException("Element:" + element + " not found!");
        }
        return remove(indexFind);
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
        T[] newArray = (T[]) new Object[(int) (array.length * GROW_FACTOR)];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void validateIndexForNewArray(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds size " + size);
        }
    }

    private void validateIndexForOldValue(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds size " + size);
        }
    }
}
