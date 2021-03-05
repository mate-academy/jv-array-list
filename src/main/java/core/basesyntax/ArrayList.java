package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private Object[] items = new Object[MAX_ITEMS_NUMBER];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == items.length) {
            grow((int) (items.length * 1.5));
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == items.length) {
            grow((int) (items.length * 1.5));
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        Object[] addArray = new Object[items.length];
        System.arraycopy(items, 0, addArray, 0, index);
        addArray[index] = value;
        System.arraycopy(items, index, addArray, index + 1, size - index);
        items = addArray;
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is wrong!");
        }
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(" ");
        }
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        // TODO: remove the `addArray`
        T removeElem;
        removeElem = (T) items[index];
        System.arraycopy(items, 0, items, 0, index);
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return removeElem;
    }

    @Override
    public T remove(T element) {
        int indexOfElem = -1;
        for (int i = 0; i < items.length; i++) {
            // TODO: collapse these if statements
            if (items[i] == null && element == null || items[i] != null
                    && items[i].equals(element)) {
                indexOfElem = i;
                break;
            }
        }
        if (indexOfElem == -1) {
            throw new NoSuchElementException("There there is no such element present");
        }
        System.arraycopy(items, indexOfElem + 1, items, indexOfElem, size - indexOfElem - 1);
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(items, 0, newArray, 0, size);
        items = newArray;
    }

    @Override
    public String toString() {
        return "ArrayList {"
                + "items = " + Arrays.toString(items)
                + ", positionOnArray = " + size
                + '}';
    }
}
