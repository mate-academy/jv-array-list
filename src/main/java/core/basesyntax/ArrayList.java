package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_ITEMS_NUMBER = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] items;
    private int size;

    public ArrayList() {
        items = (T[]) new Object[START_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        if (items.length == size) {
            grow();
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (items.length == size) {
                grow();
            }
            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = value;
            size++;
        }
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
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = findValue(element);
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

    private void grow() {
        T[] listNew = (T[]) new Object[(int) (size * INCREASE_RATE)];
        System.arraycopy(items, 0, listNew, 0, size);
        items = listNew;
    }

    private void checkIndex(int index) {
        if (index < 0 || (index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private int findValue(T element) {
        for (int i = 0; i < size; i++) {
            if (items[i] == element || items[i] != null && items[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in List");
    }
}
