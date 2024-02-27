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
        growIfArrayFull();
        if (index == sizeOfArray) {
            array[index] = value;
            sizeOfArray++;
            return;
        }
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
        for (int i = sizeOfArray - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = value;
        sizeOfArray++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = sizeOfArray + list.size();
        int indexOfArrayList = 0;
        for (int i = sizeOfArray; i < newSize; i++) {
            growIfArrayFull();
            add(list.get(indexOfArrayList));
            indexOfArrayList++;
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
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, sizeOfArray - 1 - index);
        array[--sizeOfArray] = null;
        return removed;
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < sizeOfArray; i++) {
            if (element == array[i]
                    || element != null && element.equals(array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT);
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
}
