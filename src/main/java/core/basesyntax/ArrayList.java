package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] defaultArray;
    private int size;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] getNewCapacityArray(T[] array) {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newCapacityArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newCapacityArray, 0, oldCapacity);
        return newCapacityArray;
    }

    private T[] addValueByIndex(T[] array, int index) {
        System.arraycopy(array, index, array, index + 1, size - index);
        return array;
    }

    private T[] removeValueByIndex(T[] array, int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        return array;
    }

    @Override
    public void add(T value) {
        if (size == defaultArray.length) {
            defaultArray = getNewCapacityArray(defaultArray);
            defaultArray[size] = value;
            size++;
            return;
        }
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is out of range!");
        }
        if (size == defaultArray.length) {
            defaultArray = getNewCapacityArray(defaultArray);
        }
        if (index < size) {
            defaultArray = addValueByIndex(defaultArray, index);
            defaultArray[index] = value;
            size++;
            return;
        }
        defaultArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            defaultArray[size] = list.get(i);
            size++;
            if (size == defaultArray.length) {
                defaultArray = getNewCapacityArray(defaultArray);
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is out of range!");
        }
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is out of range!");
        }
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is out of range!");
        }
        T removedElement = defaultArray[index];
        defaultArray = removeValueByIndex(defaultArray, index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == defaultArray[i] || element != null && element.equals(defaultArray[i])) {
                defaultArray = removeValueByIndex(defaultArray, i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Cannot find this element!");
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
