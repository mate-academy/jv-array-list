package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void fixArraySizeIfNeeded(int potentialSize) {
        if (array.length < potentialSize) {
            growArray();
        }
    }

    private void growArray() {
        T[] tempArray = (T[]) new Object[array.length + array.length / 2];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }

    private void checkForOutOfBoundException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't work with index " + index + ": "
                    + (size == 0 ? "list im empty" : " last index is " + (size - 1)));
        }
    }

    @Override
    public void add(T value) {
        fixArraySizeIfNeeded(size + 1);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkForOutOfBoundException(index);
        fixArraySizeIfNeeded(size + 1);
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        //rewriting value on array[index]
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listToAddSize = list.size();
        fixArraySizeIfNeeded(array.length + list.size());
        for (int i = 0; i < listToAddSize; i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkForOutOfBoundException(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkForOutOfBoundException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForOutOfBoundException(index);
        T removedValue = array[index];
        //copying right part of array to sub array for holding
        System.arraycopy(array, index + 1,
                array, index,
                size - index -1);
        //replacing previous last value with the null value
        array[--size] = null;
        //trimming array if need to
        fixArraySizeIfNeeded(size);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        //searching for element's index in array
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == null ? element == null : array[i].equals(element))) {
                //calling remove by index method
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element [" + element + "] in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            if (array == null || array[i] == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(array[i]);
            }
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
