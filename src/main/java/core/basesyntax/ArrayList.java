package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        size++;
        growIfArrayFull();
        array[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        size++;
        growIfArrayFull();
        System.arraycopy(array, index, array, index + 1, size - (index + 1));
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        T[] tempArray = (T[]) new Object[list.size()];
        int index = 0;
        while (index < list.size()) {
            tempArray[index] = list.get(index);
            index++;
        }
        resize(tempArray);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null
                    && array[i].equals(element)) {
                return remove(i);
            }
        }
            throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size >= DEFAULT_SIZE) {
            resize();
        }
    }

    private void resize() {
        T[] tempArray = (T[]) new Object[size];
        System.arraycopy(array, 0, tempArray, 0, array.length);
        array = tempArray;
    }

    private void resize(T[] newArray) {
        int tempSize = size + newArray.length;
        T[] tempArray = (T[]) new Object[tempSize];
        System.arraycopy(array, 0, tempArray, 0, size);
        System.arraycopy(newArray, 0, tempArray, size, newArray.length);
        size = tempArray.length;
        array = tempArray;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than zero");
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private T[] getArrayAfterRemove(int index) {
        size--;
        T[] tempArray = (T[]) new Object[array.length];
        System.arraycopy(array, 0, tempArray, 0, index);
        if (index < size) {
            System.arraycopy(array, index + 1, tempArray, index,array.length - (index + 1));
        }
        return tempArray;
    }
}
