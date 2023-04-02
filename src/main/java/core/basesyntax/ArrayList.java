package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_ARRAY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[])new Object[SIZE_ARRAY];
    }

    @Override
    public void add(T value) {
        growArray();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexOutOfBounds(index);
        if (size == array.length) {
            growArray();
        }
        System.arraycopy(array, index, array, index + 1,size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBounds(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Removed element: "
                + element + " does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArray() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[((int) (array.length * GROW_FACTOR))];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0 || index > size || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Array. Side:" + size
                    + " Index:" + index + ".");
        }
    }
}
