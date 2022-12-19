package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASE_ARRAY_LENGTH = 10;
    private static final double ARRAY_INCREMENT_VALUE = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[BASE_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        checkAndIncreaseArrayLength();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bounds");
        }
        checkAndIncreaseArrayLength();
        System.arraycopy(array, index, array, index + 1, size() - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            array[i + size()] = list.get(i);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        runArrayListIndexOutOfBoundsException(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size() - 1) {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        runArrayListIndexOutOfBoundsException(index);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (array[i] == null || array[i] == element || array[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException(element + " doesn't exist");
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        if (array == null) {
            return 0;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        }
        return true;
    }

    private void checkAndIncreaseArrayLength() {
        if (size() == array.length) {
            T[] increasedArray = (T[]) new Object[(int) (array.length * ARRAY_INCREMENT_VALUE)];
            System.arraycopy(array, 0, increasedArray, 0, array.length);
            array = increasedArray;
        }
    }

    private void runArrayListIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bounds");
        }
    }
}
