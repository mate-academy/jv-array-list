package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private T[] array;
    private int currentSizeOfArray = 10;
    private int size = 0;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE_OF_ARRAY];
    }

    @Override
    public void add(T value) {
        if (size == currentSizeOfArray) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
        if (size + 1 > currentSizeOfArray) {
            grow();
        }
        System.arraycopy(array,index,array,index + 1,size - index);
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
        T deletedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        int index = getIndexFromValue(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        T[] tmp = array;
        array = (T[]) new Object[(int)(currentSizeOfArray + currentSizeOfArray * 0.5)];
        System.arraycopy(tmp,0, array,0,currentSizeOfArray);
        currentSizeOfArray += currentSizeOfArray * 0.5;
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "currentSizeOfArray=" + currentSizeOfArray
                + ", array=" + Arrays.toString(array)
                + ", size=" + size + '}';
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: " + index + " is incorrect");
        }
    }

    private int getIndexFromValue(T value) {
        for (int i = 0; i < size; i++) {
            if ((array[i] == null && value == null) || (value != null && value.equals(array[i]))) {
                return i;
            }
        }
        throw new NoSuchElementException("value " + value);
    }
}
