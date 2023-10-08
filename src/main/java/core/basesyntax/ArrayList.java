package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
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
        if (index > usedSpace || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value to "
                    + "this index because arrayList size is: "
                    + usedSpace);
        }
        growIfArrayFull();
        addValueToIndex(index, value);
    }

    private void growIfArrayFull() {
        if (usedSpace == values.length - 1) {
            int oldCapacity = values.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] tempValues = new Object[newCapacity];
            System.arraycopy(values, 0, tempValues, 0, usedSpace);
            values = (T[])new Object[newCapacity];
            values = (T[]) tempValues;
        }
    }

    private void grow() {
        int oldCapacity = values.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] tempValues = new Object[newCapacity];
        System.arraycopy(values, 0, tempValues, 0, usedSpace);
        values = (T[])new Object[newCapacity];
        values = (T[]) tempValues;
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
        if (index >= usedSpace || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cant get value "
                    + "of this index because arrayList size is :"
                    + usedSpace);
        }
        return (T) values[index];
    }

    public void set(T value, int index) {
        if (index >= usedSpace || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cant set value "
                    + "to this index because arrayList size is :"
                    + usedSpace);
        }
        values[index] = value;
    }

    public T remove(int index) {
        if (index >= usedSpace || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cant remove value "
                    + "of this index because arrayList size is :"
                    + usedSpace);
        }
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
        T[] tempValues = (T[]) new Object[values.length];
        System.arraycopy(values, 0, tempValues, 0, index);
        tempValues[index] = value;
        System.arraycopy(values, index, tempValues, index + 1, usedSpace - index);
        values = tempValues;
        usedSpace++;
    }

    private void removeValueFromIndex(int index) {
        T[] tempValues = (T[]) new Object[values.length];
        System.arraycopy(values,0, tempValues,0, index);
        System.arraycopy(values, index + 1, tempValues, index, usedSpace - index);
        values = tempValues;
        usedSpace--;
    }
}
