package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_CAPACITY = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {

    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        while (size + list.size() > array.length) {
            ensureCapacity();
        }

        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && array[i].equals(element)) {
                array[i] = null;
            }
        }
        throw new NoSuchElementException("This element does not exist: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void ensureCapacity() {
        if (size == array.length) {
            int increasedSize = (int) (array.length * GROWTH_CAPACITY);
            array = Arrays.copyOf(array, increasedSize);
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
           throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
