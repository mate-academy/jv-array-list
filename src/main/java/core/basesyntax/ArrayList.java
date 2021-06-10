package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
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
                    + " is greater than array size: " + sizeOfArray);
        }
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
        System.arraycopy(values, index + 1, values, index, values.length - (index + 1));
        sizeOfArray--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeOfArray; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                T oldValue = values[i];
                System.arraycopy(values, i + 1, values, i, values.length - (i + 1));
                sizeOfArray--;
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

    private void checkCapacity() {
        if (sizeOfArray == values.length) {
            T[] oldArray = (T[]) new Object[sizeOfArray];
            System.arraycopy(values, 0, oldArray, 0, values.length);
            values = (T[]) new Object[(int) (sizeOfArray * 1.5)];
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
