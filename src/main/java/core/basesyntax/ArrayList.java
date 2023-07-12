package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_LENGTH_ARRAY = 10;
    public static final int STARTING_ARRAY_INDEX = 0;
    public static final double NUMBER_OF_GROWING_ARRAY_LENGTH = 1.5;
    public static final int AMOUNT_OF_ADDED_VALUES_TO_ARRAY = 1;
    public static final int AMOUNT_OF_REMOVED_VALUES_FROM_ARRAY = 1;
    private T[] values;

    private int size = 0;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_LENGTH_ARRAY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        addInList(value);
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            addInsideArray(value, index);
        }

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
        return removeInArray(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element
                    || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("arrayList do not contain " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void addInsideArray(T value, int index) {
        if (size + AMOUNT_OF_ADDED_VALUES_TO_ARRAY == values.length) {
            grow();
        }
        T[] tempValues = (T[]) new Object[values.length];
        System.arraycopy(values, STARTING_ARRAY_INDEX, tempValues, STARTING_ARRAY_INDEX, index);
        tempValues[index] = value;
        System.arraycopy(values, index, tempValues, index + AMOUNT_OF_ADDED_VALUES_TO_ARRAY,
                values.length - index - AMOUNT_OF_ADDED_VALUES_TO_ARRAY);
        values = tempValues;
        size++;
    }

    private void addInList(T value) {
        values[size] = value;
        size++;
    }

    private void grow() {
        T[] grownArray = (T[]) new Object[(int) (values.length * NUMBER_OF_GROWING_ARRAY_LENGTH)];
        System.arraycopy(values, STARTING_ARRAY_INDEX, grownArray, STARTING_ARRAY_INDEX, size);
        values = grownArray;
    }

    private void checkIndex(int index) {
        if (index < STARTING_ARRAY_INDEX || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of arrayList bounds");
        }
    }

    private T removeInArray(int index) {
        T[] tempValues = (T[]) new Object[values.length];
        System.arraycopy(values, STARTING_ARRAY_INDEX, tempValues, STARTING_ARRAY_INDEX, index);
        System.arraycopy(values, index + AMOUNT_OF_REMOVED_VALUES_FROM_ARRAY,
                tempValues, index, size - index - AMOUNT_OF_REMOVED_VALUES_FROM_ARRAY);
        final T removedValue = values[index];
        values = tempValues;
        size--;
        return removedValue;
    }
}
