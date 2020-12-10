package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] array;
    private int counter;

    public ArrayList() {
        array = new Object[ARRAY_SIZE];
        counter = 0;
    }

    @Override
    public void add(T value) {
        growIfRequire();
        array[counter] = value;
        counter = counter + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index == counter) {
            add(value);
        } else {
            validateIndex(index);
            growIfRequire();
            System.arraycopy(array, index, array, index + 1, counter - index);
            array[index] = value;
            counter = counter + 1;
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
        validateIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T oldValue = get(index);
        System.arraycopy(array, index + 1, array, index, counter - index - 1);
        counter = counter - 1;
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < counter; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                return remove(i);
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

    private void growIfRequire() {
        if (counter == array.length) {
            int newSize = array.length + (array.length >> 1);
            if (newSize <= 0) {
                throw new ArrayIndexOutOfBoundsException("Array is out of integer bounds and "
                    + "require hugeCapacity() implementation. It's can't be bigger right now!");
            }
            Object[] oldArray = array;
            array = new Object[newSize];
            System.arraycopy(oldArray, 0, array, 0, counter);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= counter) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds for index: " + index);
        }
    }
}
