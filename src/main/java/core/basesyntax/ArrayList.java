package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_OUT_OF_BOUNDS_MASSAGE
            = "The index value is outside the array";
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private int sizeOfArray;
    private T[] array;
    private T[] temporaryArray;

    public ArrayList() {
        array = (T[])(new Object[DEFAULT_SIZE_OF_ARRAY]);
    }

    @Override
    public void add(T value) {
        if (sizeOfArray == array.length) {
            temporaryArray = grow(sizeOfArray);
            for (int i = 0; i < array.length; i++) {
                temporaryArray[i] = array[i];
            }
            array = temporaryArray;
        }
        array[sizeOfArray] = value;
        sizeOfArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MASSAGE);
        }
        if (sizeOfArray == array.length) {
            temporaryArray = grow(sizeOfArray);
        } else {
            temporaryArray = (T[])(new Object[array.length]);
        }
        for (int i = 0; i < index; i++) {
            temporaryArray[i] = array[i];
        }
        temporaryArray[index] = value;
        sizeOfArray++;
        for (int i = ++index; i < sizeOfArray; i++) {
            temporaryArray[i] = array[i - 1];
        }
        array = temporaryArray;
    }

    @Override
    public void addAll(List<T> list) {
        int indexOfArrayList = 0;
        if ((sizeOfArray + list.size()) >= array.length) {
            temporaryArray = grow(sizeOfArray + list.size());
        } else {
            temporaryArray = (T[])(new Object[sizeOfArray + list.size()]);
        }
        for (int i = 0; i < sizeOfArray; i++) {
            temporaryArray[i] = array[i];
        }
        for (int i = sizeOfArray; i < sizeOfArray + list.size(); i++) {
            temporaryArray[i] = list.get(indexOfArrayList);
            indexOfArrayList++;
        }
        array = temporaryArray;
        sizeOfArray += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MASSAGE);
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MASSAGE);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MASSAGE);
        }
        temporaryArray = (T[])(new Object[array.length]);
        for (int i = 0; i < index; i++) {
            temporaryArray[i] = array[i];
        }
        for (int i = index + 1; i < sizeOfArray; i++) {
            temporaryArray[i - 1] = array[i];
        }
        T removedValue = array[index];
        array = temporaryArray;
        sizeOfArray--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = 0;
        temporaryArray = (T[])(new Object[array.length]);
        for (int i = 0; i < sizeOfArray; i++) {
            if (Objects.equals(element, array[i]) || indexOfElement == sizeOfArray) {
                break;
            }
            indexOfElement++;
        }
        if (indexOfElement == sizeOfArray) {
            throw new NoSuchElementException(INDEX_OUT_OF_BOUNDS_MASSAGE);
        }
        for (int i = 0; i < indexOfElement; i++) {
            temporaryArray[i] = array[i];
        }
        for (int i = indexOfElement + 1; i < sizeOfArray; i++) {
            temporaryArray[i - 1] = array[i];
        }
        array = temporaryArray;
        sizeOfArray--;
        return element;
    }

    @Override
    public int size() {
        return sizeOfArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeOfArray == 0;

    }

    private T[] grow(int nextSizeOfArray) {
        int valueOfSize = array.length;
        while (nextSizeOfArray >= valueOfSize) {
            valueOfSize = valueOfSize + (valueOfSize << 1);
        }
        return (T[])(new Object[valueOfSize]);
    }
}
