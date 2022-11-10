package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int capacity = 10;
    private static final double COEFFICIENT_GROWING = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size < capacity) {
            array[size] = value;
            size++;
        } else {
            grow(capacity, value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && size < capacity && index > -1) {
            if (capacity - 1 > size) {
                T[] arrayWithNewCapacity = (T[]) new Object[capacity + 1];
                System.arraycopy(array, index, arrayWithNewCapacity, index + 1, size - index);
                arrayWithNewCapacity[index] = value;
                System.arraycopy(array, 0, arrayWithNewCapacity, 0, index);
                array = arrayWithNewCapacity;
                size++;
            } else {
                grow(capacity, value, index);
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can`t add element with this index.");
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
        if (checkIndex(index)) {
            return array[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Can`t get element with this index");
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can`t set value with this index");
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            T result = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return result;
        }
        throw new ArrayListIndexOutOfBoundsException("Can`t remove element with this index");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null && array[i].equals(element))
                    || (array[i] == null && element == null)) {
                T result = array[i];
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return result;
            }
        }
        throw new NoSuchElementException("Such element was not found in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int capacity, T value) {
        int newCapacity = (int) (capacity * COEFFICIENT_GROWING);
        T[] arrayWithNewCapacity = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, arrayWithNewCapacity, 0, size);
        arrayWithNewCapacity[size] = value;
        array = arrayWithNewCapacity;
        ArrayList.capacity = newCapacity;
        size++;
    }

    private void grow(int capacity, T value, int index) {
        int newCapacity = (int) (capacity * COEFFICIENT_GROWING);
        T[] arrayWithNewCapacity = (T[]) new Object[newCapacity];
        System.arraycopy(array, index, arrayWithNewCapacity, index + 1, size - index);
        arrayWithNewCapacity[index] = value;
        System.arraycopy(array, 0, arrayWithNewCapacity, 0, index);
        array = arrayWithNewCapacity;
        ArrayList.capacity = newCapacity;
        size++;
    }

    private boolean checkIndex(int index) {
        return (index < size && index > -1);
    }
}
