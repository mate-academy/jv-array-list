package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final int LIST_MAX_SIZE = Integer.MAX_VALUE - 8;
    private int size;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_LENGTH];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index);
        size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        T element = get(index);
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T removed = array[index];


        size -= 1;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        return index == -1 ? null : remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return array[0] == null;
    }

    private void indexValidation(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index
                    + " is out of the range of list size " + size);
        }
    }

    private void expandArray() {
        if (array.length == LIST_MAX_SIZE) {
            throw new ArrayStoreException("List size reached maximum");
        }
        int newLength = Math.min((array.length >> 1), LIST_MAX_SIZE);
        array = Arrays.copyOf(array, newLength);
    }

    /*
    returns index of the specific element in the list
    if no such element returns -1
     */
    public int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (!array[i].equals(element)) {
                continue;
            }
            return i;
        }
        return -1;
    }
}
