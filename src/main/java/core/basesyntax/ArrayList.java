package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        resize();
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIfIndexFits(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexFits(index);
        array[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIfIndexFits(index);
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = index(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list, " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, " + index);
        }
    }

    private void checkIfIndexFits(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index in the list, "
                    + index);
        }
    }

    private void resize() {
        if (array.length == size) {
            int newCapacity = (int) (array.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private int index(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] == element
                    || array[i] != null && array[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }
}
