package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private int sizeArray = 0;
    private Object[] array = new Object[START_CAPACITY];

    @Override
    public void add(T value) {
        if (sizeArray + 1 > array.length) {
            int newSize = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, newSize);
        }
        array[sizeArray] = value;
        sizeArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index > sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        int newSize = (sizeArray + 1 > array.length) ? (int) (array.length * 1.5) : array.length;
        Object[] newArray = new Object[newSize];

        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, sizeArray - index);
        array = newArray;
        sizeArray++;
    }

    @Override
    public void addAll(List<T> list) {
        if (sizeArray + list.size() > array.length) {
            int newSize = (int) (array.length * 1.5);
            array = Arrays.copyOf(array, newSize);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= sizeArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        Object[] newArray = new Object[array.length];
        T value;

        System.arraycopy(array, 0, newArray, 0, index);
        value = (T) array[index];
        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, sizeArray - index);
        }
        array = newArray;
        sizeArray--;
        return value;
    }

    @Override
    public T remove(T element) {
        int i = 0;
        while (!Objects.equals(element, array[i]) && i < sizeArray) {
            i++;
        }
        if (i < sizeArray) {
            return remove(i);
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeArray == 0;
    }
}
