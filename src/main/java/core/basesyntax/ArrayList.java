package core.basesyntax;

import java.util.Arrays;


public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int size;
    private T[] array = (T[]) new Object[CAPACITY];

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size > array.length - 1) {
            increaseSize();
        }
        if (index < size) {
            System.arraycopy(array, index, array,
                    index + 1, array.length - 2);
            array[index] = value;
            size++;
        } else {
            array[size++] = value;
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
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final T temp = array[index];
        System.arraycopy(array, index + 1,
                array, index, array.length - 2);
        size--;
        return temp;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseSize() {
        int newCapacity = (array.length * 3 / 2) + 1;
        array = Arrays.copyOf(array, newCapacity);
    }
}
