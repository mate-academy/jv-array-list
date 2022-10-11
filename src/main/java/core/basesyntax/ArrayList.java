package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_GROWTH = 1.5;
    private static final int START_SIZE = 0;
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = START_SIZE;
    }

    @Override
    public void add(T value) {
        checkSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexInAdd(index);
        checkSize();
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, array.length - index - 1);
        array = newArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (array.length < newSize) {
            Object[] newArray = new Object[newSize];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        int counter = 0;
        for (int i = size; i < newSize; i++) {
            array[i] = list.get(counter);
            counter++;
        }
        size = newSize;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = (T) array[index];
        removeElement(index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || array[i] != null && array[i].equals(element)) {
                removeElement(i);
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int extendedArrayCapacity = (int) (array.length * ARRAY_GROWTH);
        Object[] extendedArray = new Object[extendedArrayCapacity];
        System.arraycopy(array, 0, extendedArray, 0, array.length);
        array = extendedArray;
    }

    private void checkIndexInAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    private void checkSize() {
        if (size + 1 > array.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    private void removeElement(int index) {
        int arrayLength = array.length;
        Object[] newArray = new Object[arrayLength];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, arrayLength - (index + 1));
        array = newArray;
        size--;
    }
}
