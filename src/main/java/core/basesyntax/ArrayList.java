package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[CAPACITY];
    }

    public void increaseArray() {
        T[] arrayCopy = (T[]) new Object[(int) (array.length * INCREASE_RATE)];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);
        array = arrayCopy;
    }

    @Override
    public void add(T value) {
        if (size() >= array.length) {
            increaseArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        if (size() >= array.length) {
            increaseArray();
        }
        if (index < size()) {
            System.arraycopy(array, index, array, index + 1, size() - index);
            array[index] = value;
            size++;
        }
        if (index == size()) {
            add(value);
        }

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        T value = null;
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        } else {
            value = array[index];
        }
        return value;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        } else {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        Object removeElement;
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        } else {
            removeElement = get(index);
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
        }
        return (T) removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (array[i] != null && array[i].equals(element) || array[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
