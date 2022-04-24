package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final double INCREASE_COEFFICIENT = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArray();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the Array");
        }
        arrayPlusElement(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        throwIndexOutOfBoundsException(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        throwIndexOutOfBoundsException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        throwIndexOutOfBoundsException(index);
        T indexValue = (T) array[index];
        arrayMinusElement(index);
        return indexValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                arrayMinusElement(i);
                return (T) element;
            }
        }
        throw new NoSuchElementException("No such Element in Array " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        if (size == array.length - 1) {
            Object[] newArray = new Object[(int) (array.length * INCREASE_COEFFICIENT)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void arrayMinusElement(int index) {
        System.arraycopy(array, 0, array, 0, index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    private void arrayPlusElement(T value, int index) {
        resizeArray();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void throwIndexOutOfBoundsException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds of the Array");
        }
    }
}
