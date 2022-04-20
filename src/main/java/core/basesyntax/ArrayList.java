package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final double INCREASE_COEFFICIENT = 1.5;
    private int capacity;
    private int size;
    private Object[] array;

    public ArrayList() {
        capacity = 10;
        array = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (capacity - 1 == size) {
            array = resizeArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the Array");
        } else if (capacity - 1 == size) {
            array = resizeArray();
        }
        array = arrayPlusElement(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        while (capacity - 1 < list.size() + size) {
            array = resizeArray();
        }
        for (int i = 0; i < list.size(); i++) {
            array[size + i] = list.get(i);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the Array");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the Array");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the Array");
        }
        T indexValue = (T) array[index];
        array = arrayMinusElement(index);
        return indexValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                array = arrayMinusElement(i);
                return (T) element;
            }
        }
        throw new NoSuchElementException("No such Element in Array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] resizeArray() {
        capacity = (int) (capacity * INCREASE_COEFFICIENT);
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    private Object[] arrayMinusElement(int index) {
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, size - index - 1);
        size--;
        return newArray;
    }

    private Object[] arrayPlusElement(T value, int index) {
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, size - index);
        size++;
        return newArray;
    }
}
