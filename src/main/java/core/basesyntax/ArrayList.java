package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPASITY = 10;
    private static final int NUMBER_ONE = 1;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPASITY];
    }

    @Override
    public void add(T value) {
        increaseArraySizeIfNecessary(size);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        increaseArraySizeIfNecessary(size);

        T[] secondArrayPart = getArrayCopy(array, index, array.length);
        array[index] = value;

        int startIndexForCopyArray = index + NUMBER_ONE;
        System.arraycopy(secondArrayPart, 0, array, startIndexForCopyArray,
                secondArrayPart.length - startIndexForCopyArray);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        size = size + list.size();
        increaseArraySizeIfNecessary(size);

        int startIndexForListElements = size - list.size();
        for (int i = 0; i < list.size(); i++) {
            array[startIndexForListElements + i] = list.get(i);
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
        int startIndexForCopy = index + NUMBER_ONE;
        int lastIndexOfArray = size - NUMBER_ONE;
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

    private T[] getArrayCopy(T[] inputArray, int startIndex, int endIndex) {
        T[] intermediateArray = (T[]) new Object[inputArray.length];

        System.arraycopy(inputArray, startIndex, intermediateArray, 0,
                endIndex - startIndex);
        return intermediateArray;
    }

    private void checkIndexForGet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index not correct");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index not correct");
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value || (array[i] != null && array[i].equals(value))) {
                return i;
            }
        }
        throw new NoSuchElementException("don't find such element");
    }
}
