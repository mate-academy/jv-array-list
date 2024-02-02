package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        array = growIfNeeded();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkBoundForAdd(index);
        array = growIfNeeded();
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
        checkBound(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkBound(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBound(index);
        T removeElement = array[index];
        int startIndex = index;
        int elementsToMove = size - index - 1;
        System.arraycopy(array, startIndex + 1, array, startIndex, elementsToMove);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || (element != null && element.equals(array[i]))) {
                T removeElement = array[i];
                int startIndex = i;
                int elementsToMove = size - i - 1;
                System.arraycopy(array, startIndex + 1, array, startIndex, elementsToMove);
                size--;
                return removeElement;
            }
        }
        throw new NoSuchElementException("Can't find element for remove: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] growIfNeeded() {
        if (size == array.length) {
            int oldCapacity = array.length;
            int newCapacity = (int) (oldCapacity * GROW_FACTOR);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        return array;
    }

    private void checkBound(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative :" + index);
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Bad input index: " + index
                    + "for size: " + size);
        }
    }

    private void checkBoundForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Object index cannot be "
                    + "larger than the list size");
        }
    }
}
