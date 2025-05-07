package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_SIZE_DATA = 10;
    public static final double RESIZE_INDEX = 1.5;
    private int size;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_SIZE_DATA];
    }

    @Override
    public void add(T value) {
        getArrayList();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateAddIndex(index);
        getArrayList();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
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
        validateIndex(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T deleteArray = items[index];
        size--;
        System.arraycopy(items, index + 1, items, index, size - index);
        return deleteArray;
    }

    @Override
    public T remove(T element) {
        for (int j = 0; j < size; j++) {
            if (element != null
                    && element.equals(items[j])
                    || element == items[j]) {
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

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not supported " + index);
        }
    }

    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound " + index);
        }
    }

    private void getArrayList() {
        if (size == items.length) {
            int add = (int) (size * RESIZE_INDEX);
            T[] tempArray = (T[]) new Object[add];
            System.arraycopy(items, 0, tempArray, 0, items.length);
            items = tempArray;
        }
    }
}
