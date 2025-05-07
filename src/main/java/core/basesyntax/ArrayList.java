package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLICATION_FOR_CAPACITY = 1.5;
    private static final String EXCEPTION_MESSAGE = "Index of the bounds. Check your input index!";
    private int size;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
        if (size == items.length) {
            grow();
        }
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
        checkRangeOfIndex(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkRangeOfIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRangeOfIndex(index);
        T item = items[index];
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        size--;
        return item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == items[i] || element != null && element.equals(items[i])) {
                return remove(i);
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
        T[] newItems = (T[]) new Object[(int) (items.length * MULTIPLICATION_FOR_CAPACITY)];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    private void checkRangeOfIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
    }
}
