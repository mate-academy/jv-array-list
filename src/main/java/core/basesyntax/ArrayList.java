package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPASITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPASITY];
    }

    @Override
    public void add(T value) {
        increaseArraySizeIfNecessary(size);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        increaseArraySizeIfNecessary(size);

        int startIndexForInsert = index + 1;
        System.arraycopy(array, index, array, startIndexForInsert, size - index);
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
        checkIndexForGet(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGet(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForGet(index);
        int startIndexForCopy = index + 1;
        int lastIndexOfArray = size - 1;
        T value = array[index];
        System.arraycopy(array, startIndexForCopy, array, index, lastIndexOfArray - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = getIndexByValue(element);
        remove(indexOfElement);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArraySizeIfNecessary(int inputSize) {
        if (inputSize >= array.length) {
            int lagerSize = inputSize + (inputSize >> 1);
            T[] intermediateArray = (T[]) new Object[lagerSize];

            System.arraycopy(array, 0, intermediateArray, 0, array.length);
            array = intermediateArray;
        }
    }

    private void checkIndexForGet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is not correct. ArrayList size - " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index
                    + " is not correct. ArrayList size - " + size);
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value || (array[i] != null && array[i].equals(value))) {
                return i;
            }
        }
        throw new NoSuchElementException("element " + value + " is not in the ArrayList");
    }
}
