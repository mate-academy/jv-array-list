package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T[] array;
    private int size;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexCorrect(index, index > size);
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
        isIndexCorrect(index, index >= size);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexCorrect(index, index >= size);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexCorrect(index, index > size);
        T removedElement = array[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element here");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isIndexCorrect(int index, boolean indexLessThanArraySize) {
        if (indexLessThanArraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    private void grow() {
        if (size == capacity) {
            capacity = capacity + capacity / 2;
        }
        T[] copyArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, copyArray, 0, array.length);
        array = copyArray;
    }
}
