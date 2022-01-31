package core.basesyntax;

import static java.util.Arrays.copyOf;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAXIMUM_CAPACITY = 10;
    private static final float CAPACITY_INCREASE_SIZE = 1.5F;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[MAXIMUM_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            grow();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong input index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong input index!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong input index!");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T removedElement = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong input index!");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null && array[i].equals(element)) || array[i] == element) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element in the array!");
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
        if (size == array.length) {
            array = copyOf(array, (int) (size * CAPACITY_INCREASE_SIZE));
        }
    }
}
