package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] array;
    private int arraySize;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
        arraySize = 0;
    }

    private Object[] grow() {
        return Arrays.copyOf(array, array.length + (array.length >> 1));
    }

    @Override
    public void add(T value) {
        if (arraySize == array.length) {
            array = grow();
            array[arraySize] = value;
            arraySize++;
        } else {
            array[arraySize] = value;
            arraySize++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (arraySize == array.length) {
            array = grow();
        }
        System.arraycopy(array, index,
                array, index + 1,
                arraySize - index);
        array[index] = value;
        arraySize++;
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
        return (T) array[index];
    }

    private void checkIndex(int index) {
        if (index >= arraySize) {
            throw new ArrayIndexOutOfBoundsException("Index is greater than the size of list ["
                    + arraySize + "]");
        }
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T oldValue = (T) array[index];
        System.arraycopy(array, index + 1,
                array, index,
                arraySize - index);
        arraySize--;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        int index = Arrays.asList(array).indexOf(t);
        if (index == -1) {
            throw new NoSuchElementException("Value ["
                    + t.toString() + "] is not in the list!");
        }
        T oldValue = (T) array[index];
        remove(index);
        return oldValue;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0 ? true : false;
    }

}
