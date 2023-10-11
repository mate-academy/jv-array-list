package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final int ACCURACY_OF_ONE = 1;
    private static final int GROWTH_FACTOR = 1;
    private static final int INITIAL_POSITION = 0;
    private T[] values;
    private int usedSpace;

    public ArrayList() {
        values = (T[])new Object[MAX_ITEMS_NUMBER];
    }

    public void add(T value) {
        values[usedSpace++] = value;
        growIfArrayFull();
    }

    public void add(T value, int index) {
        checkIfIndexOutOfBoundsException("Can't add value to "
                + "this index because arrayList size is: ",
                index);
        growIfArrayFull();
        addValueToIndex(index, value);
    }

    private void growIfArrayFull() {
        if (usedSpace == values.length - ACCURACY_OF_ONE) {
            int oldCapacity = values.length;
            int newCapacity = oldCapacity + (oldCapacity >> GROWTH_FACTOR);
            Object[] tempValues = new Object[newCapacity];
            System.arraycopy(values, INITIAL_POSITION, tempValues, INITIAL_POSITION, usedSpace);
            values = (T[])new Object[newCapacity];
            values = (T[]) tempValues;
        }
    }

    private void grow() {
        int oldCapacity = values.length;
        int newCapacity = oldCapacity + (oldCapacity >> GROWTH_FACTOR);
        Object[] tempValues = new Object[newCapacity];
        System.arraycopy(values, INITIAL_POSITION, tempValues, INITIAL_POSITION, usedSpace);
        values = (T[])new Object[newCapacity];
        values = (T[])tempValues;
    }

    public void addAll(List<T> list) {
        while ((values.length - usedSpace) < list.size()) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public T get(int index) {
        checkIfIndexOutOfBoundsException("Cant get value "
                + "of this index because arrayList size is :",
                index + ACCURACY_OF_ONE);
        return values[index];
    }

    public void set(T value, int index) {
        checkIfIndexOutOfBoundsException("Cant set value "
                + "to this index because arrayList size is :",
                index + ACCURACY_OF_ONE);
        values[index] = value;
    }

    public T remove(int index) {
        checkIfIndexOutOfBoundsException("Cant remove value "
                + "of this index because arrayList size is :",
                index + ACCURACY_OF_ONE);
        T value = values[index];
        removeValueFromIndex(index);
        return value;
    }

    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                T value = values[i];
                removeValueFromIndex(i);
                return value;
            }
        }
        throw new NoSuchElementException("Cant remove element "
                + "because there is no such element in the list :");
    }

    public int size() {
        return usedSpace;
    }

    public boolean isEmpty() {
        return usedSpace == 0;
    }

    private void addValueToIndex(int index, T value) {
        System.arraycopy(values, index, values, index + ACCURACY_OF_ONE, usedSpace - index);
        values[index] = value;
        usedSpace++;
    }

    private void removeValueFromIndex(int index) {
        System.arraycopy(values, INITIAL_POSITION, values, INITIAL_POSITION, index);
        System.arraycopy(values, index + ACCURACY_OF_ONE, values, index, usedSpace - index);
        usedSpace--;
    }

    private void checkIfIndexOutOfBoundsException(String message, int index) {
        if (index > usedSpace || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(message + usedSpace);
        }
    }
}
