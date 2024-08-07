package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CAPACITY_INDEX = 1.5;
    private static final int MAX_ARRAY = 10;
    private T[] element;
    private int size;

    public ArrayList() {
        element = (T[]) new Object[MAX_ARRAY];
    }

    @Override
    public void add(T value) {
        grow();
        element[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than the "
                    + "size of the array or uncorect");
        }
        grow();
        System.arraycopy(element, index, element, index + 1, size - index);
        element[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            element[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) element[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        element[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeElement = (T) element[index];
        System.arraycopy(element, index + 1, element, index, size - index - 1);
        size--;
        return removeElement;
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

    private void grow() {
        if (element.length == size) {
            int newCapacity = (int) (element.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(element, 0, newArray, 0, size);
            element = (T[]) newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than the size "
                    + "of the array or uncorrect");
        }
    }

    private int index(T indexElement) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element[i] == indexElement
                    || element[i] != null && element[i].equals(indexElement)) {
                index = i;
            }
        }
        return index;
    }
}
