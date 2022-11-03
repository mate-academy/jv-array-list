package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] arrList;
    private T value;

    public ArrayList() {
        arrList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void isIndexOutOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid");
        }
    }

    @Override
    public void add(T value) {
        for (int i = 0; i <= arrList.length; i++) {
            if (i == arrList.length) {
                grow();
            }
            if (i == size) {
                arrList[i] = value;
                size++;
                break;
            }
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid");
        }
        if (size == arrList.length) {
            grow();
        }
        for (int i = size; i > 0; i--) {
            if (index < i) {
                arrList[i] = arrList[i - 1];
            }
        }
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
        isIndexOutOfBounds(index);
        return arrList[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexOutOfBounds(index);
        arrList[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexOutOfBounds(index);
        for (int i = 0; i < size; i++) {
            if (index == i && index == size - 1) {
                value = arrList[i];
                arrList[i] = null;
                size--;
            }
            if (index == i && index < size - 1) {
                value = arrList[i];
                arrList[i] = arrList[i + 1];
                size--;
            }
            if (index < i) {
                arrList[i] = arrList[i + 1];
            }
        }
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

    public T[] grow() {
        T[] newList = arrList;
        arrList = (T[]) new Object[arrList.length + (arrList.length >> 1)];
        System.arraycopy(newList, 0, arrList, 0, newList.length);
        return arrList;
    }
}
