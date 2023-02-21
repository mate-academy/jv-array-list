package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_CAPACITY = 10;
    public static final double CAPACITY_MULTIPLIER = 1.5;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_LIST_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (checkSize()) {
            resizeList();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add " + value + " index: "
                    + index + "was incorrect!");
        }
        if (size == index) {
            add(value);
            return;
        } else if (checkSize()) {
            resizeList();
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
        checkIndex(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T listWithRemoveElement = list[index];
        System.arraycopy(list,index + 1, list, index, size - index - 1);
        size--;
        return listWithRemoveElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || (list[i] != null) && list[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element + ": not found in list!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkSize() {
        return size == list.length;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index!");
        }
    }

    public void resizeList() {
        T[] array = (T[]) new Object[(int) (list.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(list, 0, array, 0, list.length);
        list = array;
    }
}
