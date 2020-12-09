package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final int LIST_MAX_SIZE = Integer.MAX_VALUE - 8;
    private int size;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            expandArray();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        size += 1;
        if (array.length <= size) {
            expandArray();
        }
        indexValidation(index);
        T[] newArray = Arrays.copyOf(array, array.length);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, size - index);
        array = newArray;
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
        return array[index];
    }

    /*
    if value wasn't found in list then no actions provided
     */
    @Override
    public void set(T value, int index) {
        indexValidation(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size -= 1;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("The "
                    + element.toString() + " wasn't found in the list!");
        }
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

    public int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void indexValidation(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index
                    + " is out of the range of list size " + size);
        }
    }

    private void expandArray() {
        if (array.length == LIST_MAX_SIZE) {
            throw new ArrayStoreException("List size reached maximum");
        }
        int newLength = Math.min((array.length >> 1) + array.length, LIST_MAX_SIZE);
        array = Arrays.copyOf(array, newLength);
    }
}
