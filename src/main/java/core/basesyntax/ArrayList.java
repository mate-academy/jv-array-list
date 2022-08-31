package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_ITEMS_NUMBER = 10;
    private static final double INCREASE_RATE = 1.5;
    private T[] item;
    private static int size;

    public ArrayList() {
        item = (T[]) new Object[START_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        if (item.length == size) {
            grow();
        }
        item[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (index == size) {
            add(value);
        } else {
            if (item.length == size) {
                grow();
            }
            System.arraycopy(item, index, item, index + 1, size - index);
            item[index] = value;
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
        return item[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        item[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = item[index];
        System.arraycopy(item, index + 1, item, index, size - index - 1);
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
        System.arraycopy(item, 0, listNew, 0, size);
        item = listNew;
    }

    private void checkIndex(int index) {
        if (index < 0 || (index > size)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private int findValue(T element) {
        for (int i = 0; i < size; i++) {
            if (item[i] != null && item[i].equals(element) || item[i] == element) {
                return i;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in List");
    }
}
