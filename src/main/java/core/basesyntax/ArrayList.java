package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MINIMUM_LENGTH_ARRAY = 10;

    private static final double CAPACITY_MULTIPLIER = 1.5;

    private T[] values = (T[]) new Object[MINIMUM_LENGTH_ARRAY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size() == values.length) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size()) {
            add(value);
            return;
        }
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + "is not valid for size " + size + ".");
        }
        if (size() == values.length) {
            grow();
        }
        System.arraycopy(values,index,values,index + 1, size() - index);
        values[index] = value;
        size++;
    }

    private void checkPositiveValueAndIndexInSize(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + "is not valid for size " + size + ".");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkPositiveValueAndIndexInSize(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + "is not valid for size " + size + ".");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkPositiveValueAndIndexInSize(index);
        T valuesIndex = values[index];
        if (index != size - 1) {
            System.arraycopy(values, index + 1, values, index, size() - index);
        }
        size--;
        return valuesIndex;
    }

    @Override
    public T remove(T element) {
        int index = searchIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("Index is out of bound array!");
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private int searchIndex(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        T[] newValues = (T[]) new Object[(int) (values.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

}
