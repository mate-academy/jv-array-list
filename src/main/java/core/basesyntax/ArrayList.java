package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private T[] array;
    private int size;

    public ArrayList() {
        grow(DEFAULT_CAPACITY);
        array = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow(capacity + capacity / 2);
        }
        T[] copyArray = (T[]) new Object[capacity];
        System.arraycopy(array, 0, copyArray, 0, array.length);
        array = (T[]) new Object[capacity];
        System.arraycopy(copyArray, 0, array, 0, copyArray.length);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexCorrect(index, index > size);

        if (size == capacity) {
            grow(capacity + capacity / 2);
        }

        T[] copyArray = (T[]) new Object[capacity];
        for (int i = 0; i < copyArray.length; i++) {
            if (i < index) {
                copyArray[i] = array[i];
            } else if (i == index) {
                copyArray[i] = value;
            } else {
                copyArray[i] = array[i - 1];
            }
        }
        array = copyArray;
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
        System.arraycopy(array, index + 1, array, index, size - index);
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

    private void grow(int i) {
        capacity = i;
    }
}
