package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final int ONE_INDEX = 1;
    private static final int NULL_INDEX = 0;
    private static final int NULL = 0;

    private T[] array = (T[])new Object[DEFAULT_INITIAL_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        add(value,size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForNewArray(index);
        if (size == array.length) {
            grow();
        }
        if (index == size) {
            array[size] = value;
            size++;
        } else {
            for (int i = size; i >= index; i--) {
                if (i != NULL_INDEX) {
                    array[i] = array[i - ONE_INDEX];
                }
                if (i == index) {
                    array[i] = value;
                    size++;
                }
            }
        }
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = list.size() + size;
        int lastIndex = NULL_INDEX;
        while (newSize >= getCapacity()) {
            grow();
        }
        for (int i = size; i < newSize; i++) {
            array[i] = list.get(lastIndex);
            lastIndex++;
            size++;
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
        if (index == size - ONE_INDEX) {
            array[index] = null;
            size--;
            return valueToRemove;
        }
        for (int i = index; i < size; i++) {
            array[i] = array[i + ONE_INDEX];
        }
        size--;
        return valueToRemove;
    }

    @Override
    public T remove(T element) {
        int indexFind = -1;
        for (int i = NULL_INDEX; i < size; i++) {
            if ((array[i] == null && element == null)
                    || (array[i] != null && array[i].equals(element))) {
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
        return size == NULL;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[(int) (getCapacity() * 1.5)];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void validateIndexForNewArray(int index) {
        if (index < NULL_INDEX || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds size " + size);
        }
    }

    private void validateIndexForOldValue(int index) {
        if (index < NULL_INDEX || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds size " + size);
        }
    }

    public int getCapacity() {
        return array.length;
    }
}
