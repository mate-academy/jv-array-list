package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int sizeOfArray = 0;
    private T[] values;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (sizeOfArray == values.length) {
            T[] oldArray = (T[]) new Object[sizeOfArray];
            System.arraycopy(values, 0, oldArray, 0, values.length);
            values = (T[]) new Object[grow(sizeOfArray)];
            System.arraycopy(oldArray, 0, values, 0, oldArray.length);
        }
        values[sizeOfArray] = value;
        sizeOfArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is greater than array size: " + sizeOfArray);
        }
        System.arraycopy(values, index, values, index + 1, sizeOfArray - index);
        values[index] = value;
        sizeOfArray++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        System.arraycopy(array, 0, values, sizeOfArray, list.size());
        sizeOfArray = sizeOfArray + array.length;
    }

    @Override
    public T get(int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is greater than array size: " + sizeOfArray);
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        T[] oldArray = (T[]) new Object[sizeOfArray];
        System.arraycopy(values, 0, oldArray, 0, sizeOfArray);
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist");
        }
        sizeOfArray--;
        T oldValue = values[index];
        System.arraycopy(oldArray, 0, values, 0, index);
        System.arraycopy(oldArray, index + 1, values, index, oldArray.length - (index + 1));
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T[] oldArray = (T[]) new Object[sizeOfArray];
        System.arraycopy(values, 0, oldArray, 0, sizeOfArray);
        for (int i = 0; i < sizeOfArray; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                sizeOfArray--;
                T oldValue = values[i];
                System.arraycopy(oldArray, 0, values, 0, i);
                System.arraycopy(oldArray, i + 1, values, i, oldArray.length - (i + 1));
                return oldValue;
            }
        }
        throw new NoSuchElementException("Element with value " + element + " does not exist");
    }

    @Override
    public int size() {
        return sizeOfArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeOfArray == 0;
    }

    private int grow(int size) {
        return (int) (size * 1.5);
    }
}
