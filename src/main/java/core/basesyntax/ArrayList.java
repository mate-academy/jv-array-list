package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private Object[] array = new Object[ARRAY_SIZE];
    private int counter = 0;

    private void resize(int minSize) {
        int newSize = minSize + (minSize >> 1);
        if (newSize - MAX_ARRAY_SIZE >= 0) {
            throw new ArrayIndexOutOfBoundsException("Array is out of integer bounds and "
                    + "I didn't implemented hugeCapacity() yet");
        }
        Object[] oldArray = array;
        array = new Object[newSize];
        System.arraycopy(oldArray, 0, array,0, counter);
    }

    @Override
    public void add(T value) {
        if (counter >= array.length - 1) {
            resize(counter);
        }
        array[counter] = value;
        counter = counter + 1;
    }

    @Override
    public void add(T value, int index) {
        if (counter >= array.length - 1) {
            resize(counter);
        }
        System.arraycopy(array, index, array, index + 1, counter - index);
        array[index] = value;
        counter = counter + 1;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > array.length) {
            resize(list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= counter) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds for index: " + index);
        } else {
            return (T) array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= counter) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        System.arraycopy(array, index + 1, array, index, counter - index - 1);
        counter = counter - 1;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < counter; i++) {
            if (array[i] == null || array[i].equals(t)) {
                T oldValue = get(i);
                System.arraycopy(array, i + 1, array, i, counter - i - 1);
                counter = counter - 1;
                return oldValue;
            }
        }
        throw new NoSuchElementException("No such object in ArrayList");
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }
}
