package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE = 10;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        array = new Object[SIZE];
        currentSize = 0;
    }

    @Override
    public void add(T value) {
        if (currentSize == array.length) {
            grow();
        }
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index ");
        }
        if (currentSize == array.length) {
            grow();
        }
        System.arraycopy(array, index, array,
                index + 1, currentSize - index);
        array[index] = value;
        currentSize++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkingIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndex(index);

        Object oldValue = array[index];
        System.arraycopy(array, index + 1, array,
                index, currentSize - (index + 1));

        currentSize--;
        return (T) oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (t == array[i] || array[i] != null && array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("not find ");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void grow() {
        Object[] currentArray = array;
        array = new Object[(int) (array.length * 1.5)];
        System.arraycopy(currentArray, 0, array, 0, currentArray.length);
    }

    private void checkingIndex(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
