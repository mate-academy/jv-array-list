package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private T[] array;
    private int arraySize = 0;

    public ArrayList() {

        array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    private void resize() {
        int newSize = array.length + (array.length / 2);
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void add(T value) {
        if (arraySize == array.length) {
            resize();
        }
        array[arraySize] = value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        if (arraySize == array.length) {
            resize();
        }
        System.arraycopy(array, index, array, index + 1, arraySize - index);
        array[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        System.out.println("public void addAll");
        if (list.isEmpty()) {
            return;
        }
        int newSize = arraySize + list.size();
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, arraySize);
        array = newArray;
        for (int i = 0; i < list.size(); i++) {
            array[arraySize++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Can`t find this index in the array");
        } else {
            return array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < arraySize) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        T removed = array[index];
        for (int i = index; i < arraySize - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--arraySize] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            boolean match = (element == null) ? array[i] == null : element.equals(array[i]);
            if (match) {
                final T removedElement = array[i];
                for (int j = i; j < arraySize - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--arraySize] = null;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }
}
