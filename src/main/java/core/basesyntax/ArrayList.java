package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size = 0;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            capacity();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (size == array.length) {
            capacity();
        }
        if (index == size) {
            array[size] = value;
        } else if (index >= 0 && index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of bounds.");
        }
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
        validation(index);
        return array[index];

    }

    @Override
    public void set(T value, int index) {
        validation(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validation(index);
        T res = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return res;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                index = i;

            }
        }
        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Didn't find the element");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void capacity() {
        array = Arrays.copyOf(array, array.length + array.length / 2);
    }

    private void validation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + "Out of bounds");
        }
    }
}


