package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object [DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index >= 0 && index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
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
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T valueOfIndex = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return valueOfIndex;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in array" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void grow() {
        if (size == array.length) {
            int firstCapacity = array.length;
            int newCapacity = firstCapacity + (firstCapacity >> 1);
            T[] secondArray = Arrays.copyOf(array, newCapacity);
            array = secondArray;
        }
    }
}
