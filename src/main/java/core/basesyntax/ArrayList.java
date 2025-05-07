package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] dataArray;
    private int size;

    public ArrayList() {
        dataArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        dataArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size && size != dataArray.length) {
            add(value);
        } else {
            indexValidation(index);
            growIfFull();
            System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
            dataArray[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexExistence(index);
        return dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExistence(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexExistence(index);
        size--;
        Object objectToDelete = dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, size - index);
        dataArray[size] = null;
        return (T) objectToDelete;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (areObjectsEquals(element, dataArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There aren't such elements in the list like " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        if (dataArray.length == size) {
            Object[] fullArray = dataArray;
            dataArray = (T[]) new Object[(int) (fullArray.length * GROW_FACTOR)];
            System.arraycopy(fullArray, 0, dataArray, 0, fullArray.length);
        }
    }

    private void indexValidation(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkIndexExistence(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private boolean areObjectsEquals(T element, Object listElement) {
        return (element == null && listElement == null)
                || (element != null && element.equals(listElement));
    }
}
