package core.basesyntax;

import java.util.Arrays;
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
        T indexElement = array[index];
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        size--;
        return indexElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == null && array[i] == null) {
                return remove(i);
            }
            if (array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element + " doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkAndIncreaseArrayLength() {
        if (size == array.length) {
            T[] increasedArray = (T[]) new Object[(int) (array.length * ARRAY_INCREMENT_VALUE)];
            System.arraycopy(array, 0, increasedArray, 0, array.length);
            array = increasedArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bounds");
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }
}
