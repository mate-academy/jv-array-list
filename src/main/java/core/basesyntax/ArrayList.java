package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[ARRAY_SIZE];
    }

    private void grow() {
        Object[] newArray = new Object[(int) (array.length * 1.5)];
        System.arraycopy(array,0, newArray,0, array.length);
        array = newArray;
    }

    @Override
    public void add(T value) {
        if (size >= array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value at index: " + index);
        }
        if (array[array.length - 1] != null) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, array.length - (index + 1));
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't get value at index: " + index);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't set value at index: " + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove value at index: "
                    + index);
        }
        T value = (T) array[index];
        if (size - 1 == index) {
            array[index] = null;
        } else {
            System.arraycopy(array, index + 1, array, index, array.length - (index + 1));
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == (T) array[i] || element != null
                    && element.equals((T) array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no value: " + element);
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
