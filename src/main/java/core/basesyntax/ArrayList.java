package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE_DATA = 10;
    public static final double INCREASE_BY = 1.5;
    private int size;
    private T[] dataType;

    public ArrayList() {
        dataType = (T[]) new Object[DEFAULT_SIZE_DATA];
    }

    @Override
    public void add(T value) {
        getArrayList();
        dataType[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        getIndexFrom(index);
        getArrayList();
        System.arraycopy(dataType,index,dataType,index + 1, size - index);
        dataType[index] = value;
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
        getIndex(index);
        return dataType[index];
    }

    @Override
    public void set(T value, int index) {
        getIndex(index);
        dataType[index] = value;
    }

    @Override
    public T remove(int index) {
        getIndex(index);
        T deleteArray = dataType[index];
        size--;
        System.arraycopy(dataType,index + 1, dataType,index, size - index);
        return deleteArray;
    }

    @Override
    public T remove(T element) {
        for (int j = 0; j < size; j++) {
            if (element != null
                    && element.equals(dataType[j])
                    || element == dataType[j]) {
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

    private void getIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not supported" + index);
        }
    }

    private void getArrayList() {
        if (size == dataType.length) {
            int add = (int) (size * INCREASE_BY);
            T[] tempArray = (T[]) new Object[add];
            System.arraycopy(dataType, 0, tempArray,0,dataType.length);
            dataType = tempArray;
        }
    }

    private void getIndexFrom(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not supported" + index);
        }
    }
}

