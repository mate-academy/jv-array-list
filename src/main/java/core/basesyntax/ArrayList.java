package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final int FIRST_INDEX = 0;
    private static final int NEXT_INDEX = 1;
    private int size;
    private Object[] array;

    {
        array = new Object[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        array[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, true);
        checkCapacity();
        System.arraycopy(array, index, array, index + NEXT_INDEX, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, false);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        Object element = array[index];
        if (index == array.length - 1) {
            array[index] = null;
        } else {
            System.arraycopy(array, index + NEXT_INDEX, array, index, size - index);
        }
        size--;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
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

    private void checkCapacity() {
        if (array.length == size) {
            grow();
        }
    }

    private void grow() {
        Object[] tmpArray = new Object[size + (size >> 1)];
        System.arraycopy(array, FIRST_INDEX, tmpArray, FIRST_INDEX, size);
        array = tmpArray;
    }

    private void checkIndex(int index, boolean forAdd) {
        if (forAdd) {
            if (index > size || index < FIRST_INDEX) {
                throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
            }
        } else if (index >= size || index < FIRST_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private int indexOf(T element) {
        for (int index = 0; index < array.length; index++) {
            if (elementsEquals(array[index], element)) {
                return index;
            }
        }
        throw new NoSuchElementException("No such element:" + element);
    }

    private boolean elementsEquals(Object element1, Object element2) {
        return element1 == null && element2 == null
                || element1 != null && element1.equals(element2);
    }

}
