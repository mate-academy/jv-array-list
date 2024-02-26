package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE
            = "The index value is outside the array";
    private static final String NO_SUCH_ELEMENT = "The list does not contain such element";
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private int sizeOfArray;
    private T[] array;

    public ArrayList() {
        array = (T[])(new Object[DEFAULT_SIZE_OF_ARRAY]);
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[sizeOfArray] = value;
        sizeOfArray++;

    }

    @Override
    public void add(T value, int index) {
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
        growIfArrayFull();
        if (sizeOfArray == 0 || index == sizeOfArray) {
            array[index] = value;
            sizeOfArray++;
            return;
        }
        T[] temporaryArray = createTemporaryArray();
        System.arraycopy(array, 0, temporaryArray, 0, index);
        temporaryArray[index] = value;
        System.arraycopy(array, index, temporaryArray, index + 1, sizeOfArray);
        array = temporaryArray;
        sizeOfArray++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = sizeOfArray + list.size();
        int indexOfArrayList = 0;
        for (int i = sizeOfArray; i < newSize; i++) {
            growIfArrayFull();
            array[i] = list.get(indexOfArrayList);
            indexOfArrayList++;
            sizeOfArray++;

        }
    }

    @Override
    public T get(int index) {
        assertIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        assertIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        assertIndex(index);
        T[] temporaryArray = createTemporaryArray();
        System.arraycopy(array, 0, temporaryArray, 0, index);
        if (++index < sizeOfArray) {
            System.arraycopy(array, index, temporaryArray, --index, sizeOfArray);
        } else {
            index--;
        }
        T removedValue = array[index];
        array = temporaryArray;
        sizeOfArray--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = 0;
        for (int i = 0; i < sizeOfArray; i++) {
            if ((element == array[i]
                    || element != null && element.equals(array[i]))
                    || indexOfElement == sizeOfArray) {
                break;
            }
            indexOfElement++;
        }
        if (indexOfElement == sizeOfArray) {
            throw new NoSuchElementException(NO_SUCH_ELEMENT);
        }
        T[] temporaryArray = createTemporaryArray();
        System.arraycopy(array, 0, temporaryArray, 0, indexOfElement);
        if (++indexOfElement < sizeOfArray) {
            System.arraycopy(array, indexOfElement, temporaryArray, --indexOfElement, sizeOfArray);
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

    private void growIfArrayFull() {
        if (sizeOfArray == array.length) {
            T[] biggerArray = (T[]) (new Object[array.length + (array.length >> 1)]);
            System.arraycopy(array, 0, biggerArray, 0, array.length);
            array = biggerArray;
        }
    }

    private void assertIndex(int index) {
        if (index == sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private T[] createTemporaryArray() {
        return (T[])(new Object[array.length]);
    }

}
