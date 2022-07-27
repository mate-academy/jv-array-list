package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INITIAL_ARRAY_CAPACITY = 10;
    private Object[] values;
    private int size;
    private int capacityOfArray;

    public ArrayList() {
        capacityOfArray = DEFAULT_INITIAL_ARRAY_CAPACITY;
        values = new Object[capacityOfArray];
    }

    @Override
    public void add(T value) {
        if (size == capacityOfArray) {
            growArray();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (size() == capacityOfArray) {
            growArray();
        }
        if (index == size()) {
            values[size()] = value;
            size++;
        } else if (index < size()) {
            Object[] newValuesOne = new Object[capacityOfArray];
            Object[] newValuesTwo = new Object[capacityOfArray];
            System.arraycopy(values, 0, newValuesOne, 0, index);
            System.arraycopy(values, index, newValuesTwo, 0, capacityOfArray - index);
            values = newValuesOne;
            values[index] = value;
            System.arraycopy(newValuesTwo, 0, values, index + 1, capacityOfArray - index - 1);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size() > capacityOfArray) {
            growArray();
        }
        Object[] valuesFmArrayList = new Object[capacityOfArray];
        for (int i = 0; i < list.size(); i++) {
            valuesFmArrayList[i] = list.get(i);
        }
        System.arraycopy(valuesFmArrayList, 0, values, size(), list.size());
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        final T removedValue = (T) values[index];
        Object[] newValuesOne = new Object[capacityOfArray];
        System.arraycopy(values, index + 1, newValuesOne, 0, capacityOfArray - index - 1);
        System.arraycopy(newValuesOne, 0, values, index, capacityOfArray - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if ((values[index] == element) || (values[index] != null
                    && values[index].equals(element))) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("Element was not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        Object[] newValues = new Object[capacityOfArray + capacityOfArray / 2];
        System.arraycopy(values, 0, newValues, 0, capacityOfArray);
        values = newValues;
        capacityOfArray += capacityOfArray / 2;
    }
}
