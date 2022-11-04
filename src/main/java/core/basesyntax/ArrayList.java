package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] arrList;
    private T value;

    public ArrayList() {
        arrList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrList.length) {
            grow();
        }
        arrList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid. Index is "
                    + index + ". But it must be in bounds from 0 to " + size);
        }
        if (size == arrList.length) {
            grow();
        }
        System.arraycopy(arrList, index, arrList, index + 1, size - index);
        arrList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = size; i < size + list.size(); i++) {
            if (size + list.size() > arrList.length) {
                grow();
            }
            arrList[i] = list.get(i - size);
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arrList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        value = arrList[index];
        System.arraycopy(arrList, index + 1, arrList, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int countOfEqualElement = 0;
        for (int i = 0; i < size; i++) {
            if (arrList[i] == element || arrList[i] != null && arrList[i].equals(element)) {
                countOfEqualElement++;
                remove(i);
                break;
            }
        }
        if (countOfEqualElement == 0) {
            throw new NoSuchElementException("There is no such element in array");
        }
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] newList = arrList;
        arrList = (T[]) new Object[arrList.length + (arrList.length >> 1)];
        System.arraycopy(newList, 0, arrList, 0, newList.length);
        return arrList;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid. Index is "
                    + index + ", but it must be in bounds from 0 and less than " + size);
        }
    }
}
