package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bonds exception for index "
                    + index);
        }
        grow();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    private void grow() {
        T[] arrayWithNewCapacity = (T[]) new Object[getNewCapacity(values.length)];
        System.arraycopy(values, 0, arrayWithNewCapacity, 0, values.length);
        values = arrayWithNewCapacity;
    }

    private int getNewCapacity(int currentCapacity) {
        if (size + 1 > currentCapacity) {
            return currentCapacity + (currentCapacity >> 1);
        }
        return currentCapacity;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }
 
    @Override
    public T get(int index) {
        checkIndexForRemoveGetSetMethods(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForRemoveGetSetMethods(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForRemoveGetSetMethods(index);
        T removedElement = values[index];
        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if ((values[i] == element)
                    || (values[i] != null && values[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElementException for element "
                + element);
    }

    private void checkIndexForRemoveGetSetMethods(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bonds exception for index "
                    + index);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
