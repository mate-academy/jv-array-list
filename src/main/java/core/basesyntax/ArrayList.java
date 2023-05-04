package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final Float MULTIPLIER_SIZE = 1.5f;
    private static final String EXCEPTION_MESSAGE_INDEX = "ArrayList index out of bounds exception";
    private static final String EXCEPTION_MESSAGE_ELEMENT = "Element not found";
    private int size;
    private T[] list;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            changeSizeList();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == list.length) {
            changeSizeList();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX + " " + index);
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        checkIndexException(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexException(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexException(index);
        T returnElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - (index + 1));
        size--;
        return returnElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
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

    private void checkIndexException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE_INDEX + " " + index);
        }
    }

    private int findIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((list[i] == element) || (list[i] != null && list[i].equals(element))) {
                return i;
            }
        }
        throw new NoSuchElementException(EXCEPTION_MESSAGE_ELEMENT);
    }

    private void changeSizeList() {
        T[] newList = (T[]) new Object[(int) (size * MULTIPLIER_SIZE)];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }
}
