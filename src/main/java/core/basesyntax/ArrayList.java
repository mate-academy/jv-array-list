package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final double arrayVolumeAdd = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object [DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {

        if (size == elementData.length) {
            grow(size);
        }
        elementData[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, 0, size);
        if (size == elementData.length) {
            grow(size);
        }
        System.arraycopy(elementData,index,elementData,index + 1,size - index);
        elementData[index] = value;
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
        checkIndex(index, 0, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, 0, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, 0, size - 1);
        final T element = (T)elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;

        return element;

    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                checkIndex(i, 0, size - 1);
                remove(i);
                return element;
            }
        }

        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private void checkIndex(int index, int min, int max) {
        if (index < min || index > max) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void grow(int minCapacity) {
        minCapacity *= arrayVolumeAdd;
        T[] extendedArray = (T[]) new Object[minCapacity];
        System.arraycopy(elementData,0,extendedArray,0,elementData.length);
        elementData = extendedArray;
    }
}
