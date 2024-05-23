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
        checkEnoughArraySize(size);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        checkEnoughArraySize(size);

        T[] secondArrayPart = getArrayCopy(array, index, array.length);
        array[index] = value;

        int startPointForIteration = index + NUMBER_ONE;
        for (int i = startPointForIteration; i < secondArrayPart.length; i++) {
            array[i] = secondArrayPart[i - startPointForIteration];
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        size = size + list.size();
        checkEnoughArraySize(size);

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
        int startPointForCopySecondPart = index + 1;
        T[] secondArrayPart = getArrayCopy(array, startPointForCopySecondPart, array.length);
        T value = array[index];

        for (int i = index; i < secondArrayPart.length; i++) {
            array[i] = secondArrayPart[i - index];
        }
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

    private void checkEnoughArraySize(int inputSize) {
        if (inputSize >= array.length) {
            int lagerSize = inputSize + (inputSize >> 1);
            T[] intermediateArray = (T[]) new Object[lagerSize];

            System.arraycopy(array, 0, intermediateArray, 0, array.length);
            array = intermediateArray;
        }
    }

    private T[] getArrayCopy(T[] inputArray, int startIndex, int endIndex) {
        T[] intermediateArray = (T[]) new Object[inputArray.length];

        for (int i = startIndex; i < endIndex; i++) {
            intermediateArray[i - startIndex] = inputArray[i];
        }
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
        boolean isValueExist = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == value || (array[i] != null && array[i].equals(value))) {
                index = i;
                isValueExist = true;
                break;
            }
        }
        if (!isValueExist) {
            throw new NoSuchElementException("don't find such element");
        }
        return index;
    }
}
