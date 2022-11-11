package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double COEFFICIENT_GROWING = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            size++;
        } else {
            grow();
            array[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if(index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid");
        }
        if(size == array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size ++;
//        if (size < array.length - 1) {
//            if (index > -1 && index <= size) {
//                System.arraycopy(array, index, array, index + 1, size - index);
//                array[index] = value;
//                size++;
//            } else {
//                throw new ArrayListIndexOutOfBoundsException("Index is not valid");
//            }
//        } else {
//            grow();
//            System.arraycopy(array, index, array, index + 1, size - index);
//            array[index] = value;
//            size++;
//        }
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
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            array[index] = value;
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
        return null;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null && array[i].equals(element))
                    || (array[i] == null && element == null)) {
                return remove(i);
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

    private void grow() {
        int newCapacity = (int) (array.length * COEFFICIENT_GROWING);
        T[] arrayWithNewCapacity = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, arrayWithNewCapacity, 0, size);
        array = arrayWithNewCapacity;
    }

    private boolean checkIndex(int index) {
        if (index < size && index > -1) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is not valid");
    }
}
