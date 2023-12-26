package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VALUE = 10;
    private static final double MORE_SIZE = 1.5;

    private T[] array;;

    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_VALUE];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        ensureCapacity();
        size++;
        System.arraycopy(array, index, array, index + 1, size - index - 1);
        array[index] = value;
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
        return  array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object[] tempArray = new Object[size - index - 1];
        final T removedElement = array[index];
        System.arraycopy(array, index + 1, tempArray, 0, tempArray.length);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        System.arraycopy(tempArray, 0, array, index, tempArray.length);
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found for removal: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int extension = (int) (array.length * MORE_SIZE);
            T [] newArray = (T[]) new Object[extension];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
