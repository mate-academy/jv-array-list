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

        if (index <= size) {
            T[] secondArrayPart = getArrayCopy(array, index, array.length);
            array[index] = value;

            int startPointForIteration = index + NUMBER_ONE;
            for (int i = startPointForIteration; i < secondArrayPart.length; i++) {
                array[i] = secondArrayPart[i - startPointForIteration];
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkEnoughArraySize(size + list.size());

        for (int i = 0; i < list.size(); i++) {
            array[i + size] = list.get(i);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        checkIndexForGet(index);
        T value = null;

        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                value = array[i];
            }
        }
        return value;
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
        boolean isRemoved = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                remove(i);
                isRemoved = true;
                break;
            }
        }
        if (!isRemoved) {
            throw new NoSuchElementException("don't find such element");
        }
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

            for (int i = 0; i < array.length; i++) {
                intermediateArray[i] = array[i];
            }
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
        if (index < 0 || index >= array.length) {
            throw new ArrayListIndexOutOfBoundsException("index not correct");
        }
    }
}
