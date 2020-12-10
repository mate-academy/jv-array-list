package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double COEFFICIENT = 0.5;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = resize(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckForAddAndRemove(index);
        if (size + 1 == array.length) {
            array = resize(array);
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
        indexCheckForGet(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheckForSet(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheckForAddAndRemove(index);
        T result = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Value not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] resize(T[] array) {
        T[] newArray = (T[]) new Object[(int) (array.length * COEFFICIENT) + array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private void indexCheckForAddAndRemove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("The index " + index + " is invalid");
        }
    }

    private void indexCheckForGet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("The index " + index + " is invalid");
        }
    }

    private void indexCheckForSet(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("The index " + index + " is invalid");
        }
    }
}
