package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double COEFFICIENT = 0.5;
    private Object[] array = new Object[CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            size++;
            return;
        }
        Object[] oldArray = new Object[array.length];
        System.arraycopy(array, 0, oldArray, 0, array.length);
        array = new Object[(int) (array.length * COEFFICIENT) + array.length];
        System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("We can't add value on this position because "
                    + "index > size" + index + " > " + size);
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
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("This element didn't exists" + index);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < size) {
            array[index] = value;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("We can't add value on this position because "
                + "index > size" + index + " > " + size);
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("We can't add value on this position because "
                    + "index > size" + index + " > " + size);
        }
        T result = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
                return (T) remove(i);
            }
        }
        throw new NoSuchElementException("Value don't found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
