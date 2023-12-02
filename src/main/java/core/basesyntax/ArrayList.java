package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final int ACCURACY_OF_ONE = 1;
    private static final int GROWTH_FACTOR = 1;
    private static final int INITIAL_POSITION = 0;
    private static final String MESSAGE_AT_REMOVE_EXCEPTION = "Cant remove value "
            + "of this index because arrayList size is :";
    private static final String MESSAGE_AT_ADD_EXCEPTION = "Can't add value to "
            + "this index because arrayList size is: ";
    private static final String MESSAGE_AT_GET_EXCEPTION = "Cant get value "
            + "of this index because arrayList size is :";
    private static final String MESSAGE_AT_SET_EXCEPTION = "Cant set value "
            + "to this index because arrayList size is :";
    private static final String MESSAGE_AT_REMOVE_NO_SUCH_ELEMENT_EXCEPTION = "Cant remove element "
            + "because there is no such element in the list :";
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    public void add(T value) {
        values[size++] = value;
        growIfArrayFull();
    }

    public void add(T value, int index) {
        checkIfIndexOutOfBoundsException(MESSAGE_AT_ADD_EXCEPTION,
                index);
        growIfArrayFull();
        System.arraycopy(values, index, values, index + ACCURACY_OF_ONE, size - index);
        values[index] = value;
        size++;
    }

    private void growIfArrayFull() {
        if (size == values.length) {
            int newCapacity = size + (size >> GROWTH_FACTOR);
            Object[] tempValues = new Object[newCapacity];
            System.arraycopy(values, INITIAL_POSITION, tempValues, INITIAL_POSITION, size);
            values = (T[])tempValues;
        }
    }

    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public T get(int index) {
        checkIfIndexOutOfBoundsException(MESSAGE_AT_GET_EXCEPTION,
                index + ACCURACY_OF_ONE);
        return values[index];
    }

    public void set(T value, int index) {
        checkIfIndexOutOfBoundsException(MESSAGE_AT_SET_EXCEPTION,
                index + ACCURACY_OF_ONE);
        values[index] = value;
    }

    public T remove(int index) {
        checkIfIndexOutOfBoundsException(MESSAGE_AT_REMOVE_EXCEPTION, index + ACCURACY_OF_ONE);
        T removedValue = values[index];
        System.arraycopy(values, index + ACCURACY_OF_ONE,
                values, index, size - index - ACCURACY_OF_ONE);
        size--;
        return removedValue;
    }

    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                T value = values[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException(MESSAGE_AT_REMOVE_NO_SUCH_ELEMENT_EXCEPTION);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIfIndexOutOfBoundsException(String message, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(message + size);
        }
    }
}
