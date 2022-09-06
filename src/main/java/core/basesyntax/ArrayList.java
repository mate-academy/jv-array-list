package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Entered index is out of bounds");
        }
        if (size == array.length) {
            grow();
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
        T removedElement = array[index];
        size--;
        System.arraycopy(array,index + 1, array, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element,array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " was not found in the ArrayList");
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
        T[] increasedArray = (T[]) new Object[(int) (size * INCREASE_RATE)];
        System.arraycopy(array, 0, increasedArray, 0, size);
        array = increasedArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Entered index is out of bounds");
        }
    }
}
