package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private Object[] items = new Object[MAX_ITEMS_NUMBER];
    private int size = 0;
    private final int rateGrow = MAX_ITEMS_NUMBER / 2;

    @Override
    public void add(T value) {
        if (size == items.length) {
            grow(items.length + rateGrow);
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == items.length) {
            grow(items.length + rateGrow);
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
        Object[] addArray = new Object[size - 1];
        T removeElem;
        removeElem = (T) items[index];
        System.arraycopy(items, 0, addArray, 0, index);
        System.arraycopy(items, index + 1, addArray, index, size - index - 1);
        items = addArray;
        size--;
        return removeElem;
    }

    @Override
    public T remove(T element) {
        Object[] addArray = new Object[size - 1];
        int indexOfElem = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null && element == null) {
                indexOfElem = i;
            }
            if (items[i] != null && items[i].equals(element)) {
                indexOfElem = i;
                break;
            }
        }
        if (indexOfElem == -1) {
            throw new NoSuchElementException("There there is no such element present");
        }
        System.arraycopy(items, 0, addArray, 0, indexOfElem);
        System.arraycopy(items, indexOfElem + 1, addArray, indexOfElem,
                size - indexOfElem - 1);
        items = addArray;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return items.length == 0 || size == 0;
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
                + ", rateGrow = " + rateGrow
                + '}';
    }
}
