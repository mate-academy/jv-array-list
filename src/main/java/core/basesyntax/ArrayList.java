package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        expandingArray();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        expandingArray();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index doesn't exist");
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexException(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexException(index);
        T deletedIndex = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return deletedIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandingArray() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[size + size / 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void indexException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist");
        }
    }
}
