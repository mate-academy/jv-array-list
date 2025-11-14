package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] array;
    private int arraySize = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (arraySize >= array.length) {
            grow();
        }
        array[arraySize] = value;
        arraySize += 1;
    }

    @Override
    public void add(T value, int index) {

        checkIndex(index, arraySize);
        if (arraySize == array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, arraySize - index);
        arraySize += 1;
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, arraySize - 1);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, arraySize - 1);
        array[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index, arraySize - 1);
        T temporaryValue = get(index);
        arraySize -= 1;
        refillArray(index);
        return temporaryValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Value doesn't exist");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private void grow() {
        T[] temporaryArray = array;
        array = (T[]) new Object[(int) (temporaryArray.length * GROWTH_FACTOR)];
        System.arraycopy(temporaryArray, 0, array, 0, arraySize);
    }

    private void checkIndex(int index, int bound) throws ArrayListIndexOutOfBoundsException {
        if (index > bound || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
    }

    private void refillArray(int index) {
        System.arraycopy(array, index + 1, array, index, arraySize - index);
        array[arraySize] = null;
    }
}
