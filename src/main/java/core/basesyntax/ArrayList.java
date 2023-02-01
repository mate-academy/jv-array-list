package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_DEFAULT_LENGTH = 10;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        array = new Object[ARRAY_DEFAULT_LENGTH];
        currentSize = 0;
    }

    @Override
    public void add(T value) {
        lengthCheck();
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        try {
            lengthCheck();
            Object[] arrayTail = Arrays.copyOfRange(array, index, currentSize);
            array[index] = value;
            System.arraycopy(arrayTail, 0, array, index + 1, arrayTail.length);
            currentSize++;
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listArr = new Object[list.size()];
        lengthCheck();
        for (int i = 0; i < list.size(); i++) {
            listArr[i] = list.get(i);
        }
        System.arraycopy(listArr, 0, array, currentSize, listArr.length);
        System.out.println(array);
    }

    @Override
    public T get(int index) {
        try {
            return (T) array[index];
        } catch (RuntimeException e) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative");
        }
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void lengthCheck() {
        if (currentSize == array.length) {
            grow();
        }
    }

    private void grow() {
        array = Arrays.copyOf(array, currentSize + (currentSize >> 1));
    }
}
