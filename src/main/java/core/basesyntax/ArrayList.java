package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLIED_VALUE = 1.5;
    private int sizeOfArray;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        values[sizeOfArray] = value;
        sizeOfArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index > sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is invalid for array size: " + sizeOfArray);
        }
        checkCapacity();
        System.arraycopy(values, index, values, index + 1, sizeOfArray - index);
        values[index] = value;
        sizeOfArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = values[index];
        System.arraycopy(values, index + 1, values, index, sizeOfArray - (index + 1));
        sizeOfArray--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
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

    private void checkCapacity() {
        if (sizeOfArray == values.length) {
            T[] oldArray = values;
            values = (T[]) new Object[(int) (sizeOfArray * MULTIPLIED_VALUE)];
            System.arraycopy(oldArray, 0, values, 0, oldArray.length);
        }
    }

    private void checkIndex(int index) {
        if (index >= sizeOfArray || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is greater than array size: " + sizeOfArray);
        }
    }
}
