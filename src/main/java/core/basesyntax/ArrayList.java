package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE_DATA = 10;
    public static final double RESIZE_INDEX = 1.5;
    private int size;
    private T[] dataObject;

    public ArrayList() {
        dataObject = (T[]) new Object[DEFAULT_SIZE_DATA];
    }

    @Override
    public void add(T value) {
        getArrayList();
        dataObject[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        getIndexFrom(index);
        getArrayList();
        System.arraycopy(dataObject, index, dataObject, index + 1, size - index);
        dataObject[index] = value;
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
        indexValidation(index);
        return dataObject[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        dataObject[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T deleteArray = dataObject[index];
        size--;
        System.arraycopy(dataObject, index + 1, dataObject, index, size - index);
        return deleteArray;
    }

    @Override
    public T remove(T element) {
        for (int j = 0; j < size; j++) {
            if (element != null
                    && element.equals(dataObject[j])
                    || element == dataObject[j]) {
                return remove(j);
            }
        }
        throw new NoSuchElementException("Element not found" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not supported" + index);
        }
    }

    private void getIndexFrom(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not supported" + index);
        }
    }

    private void getArrayList() {
        if (size == dataObject.length) {
            int add = (int) (size * RESIZE_INDEX);
            T[] tempArray = (T[]) new Object[add];
            System.arraycopy(dataObject, 0, tempArray, 0, dataObject.length);
            dataObject = tempArray;
        }
    }
}



