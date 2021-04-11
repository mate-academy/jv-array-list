package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private T[] items;
    private int size;
    private int capacity;

    public ArrayList() {
        items = (T[]) new Object[MAX_ITEMS_NUMBER];
        capacity = MAX_ITEMS_NUMBER;
    }

    @Override
    public void add(T value) {
        if (size + 1 > capacity) {
            grow();
        }
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index < 0, "ArrayList out of bounds, negative index");
        indexCheck(index > size + 1, "ArrayList out of bounds");
        if (index == size) {
            add(value);
            return;
        }
        if (size + 1 > capacity) {
            grow();
        }
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > capacity) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            items[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index < 0, "ArrayList out of bounds, negative index");
        indexCheck(index > size - 1, "Array out of bounds");
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index < 0, "ArrayList out of bounds, negative index");
        indexCheck(index > size - 1, "ArrayList out of bounds");
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index < 0, "ArrayList out of bounds, negative index");
        indexCheck(index > size - 1, "ArrayList out of bounds");
        final T temp = (T) items[index];
        for (int i = index; i < size; i++) {
            items[i] = items[i + 1];
        }
        items[size] = null;
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (items[i] == null && element == null) {
                remove(i);
                return element;
            } else if (items[i] == null) {
                continue;
            }
            if (items[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] tempArray = (T[]) new Object[(int) (items.length * 1.5)];
        System.arraycopy(items, 0, tempArray, 0, items.length);
        items = tempArray;
        capacity = items.length;
    }

    private void indexCheck(boolean b, String s) {
        if (b) {
            throw new ArrayListIndexOutOfBoundsException(s);
        }
    }
}
