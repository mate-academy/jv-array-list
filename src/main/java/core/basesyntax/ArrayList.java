package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[ARRAY_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        grow();

        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        grow();

        System.arraycopy(array, index, array, index + 1, size - index);

        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        grow();

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForAccess(index);

        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForAccess(index);

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForAccess(index);

        final T tempValue = get(index);

        System.arraycopy(array, index + 1, array, index, size - index - 1);

        size--;
        array[size] = null;
        return tempValue;
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
        if (array.length == size) {
            int newCapacity = (int) (array.length * CAPACITY_INDEX);
            Object[] tempArray = new Object[newCapacity];
            System.arraycopy(array, 0, tempArray, 0, size);
            array = tempArray;
        }
    }

    private void checkIndexForAccess(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private int index(T element) {
        int index = -1;

        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                index = i;
            }
        }

        return index;
    }
}
