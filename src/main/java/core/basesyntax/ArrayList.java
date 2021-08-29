package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;
    private int minCapacity;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.minCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        this.array = grow(minCapacity);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddMethod(index);
        if (index + 1 <= minCapacity) {
            this.array = grow(minCapacity);
            T[] newArray = grow(minCapacity);
            System.arraycopy(this.array, 0, newArray, 0, index);
            newArray[index] = value;
            System.arraycopy(this.array, index, newArray, index + 1, minCapacity - index - 1);
            copyArray(newArray, this.array);
            size++;
        } else {
            add(value);
        }
    }

    private T[] grow(int minCapacity) {
        T[] arrayWithNewCapacity = (T[]) new Object[getNewCapacity(minCapacity)];
        copyArray(this.array, arrayWithNewCapacity);
        return arrayWithNewCapacity;
    }

    private int getNewCapacity(int minCapacity) {
        if (size + 1 > array.length) {
            return this.minCapacity = minCapacity + (minCapacity >> 1);
        }
        return minCapacity;
    }

    private void checkIndexForAddMethod(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bonds exception for index "
                    + index);
        }
    }

    private void copyArray(T[] arrayCopyFrom, T[] arrayCopyTo) {
        System.arraycopy(arrayCopyFrom, 0, arrayCopyTo, 0, this.array.length);
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
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForRemoveGetSetMethods(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForRemoveGetSetMethods(index);
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, minCapacity - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == null && element == null)
                    || (array[i] != null && array[i].equals(element))) {
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
