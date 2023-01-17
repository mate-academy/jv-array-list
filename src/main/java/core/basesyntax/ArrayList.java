package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int stSize = 10;
    private int realSize = 0;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[stSize];
    }

    private void resize() {
        if (realSize >= stSize) {
            stSize *= 1.5;
            T[] newItems = (T[]) new Object[stSize];
            System.arraycopy(items, 0, newItems, 0, realSize);
            items = newItems;
        }
    }

    private void testIndexOk(int index) {
        if (index < 0 || index >= realSize) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid: " + index);
        }
    }

    @Override
    public void add(T value) {
        resize();
        items[realSize] = value;
        realSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index == realSize) {
            add(value);
            return;
        }
        testIndexOk(index);
        resize();
        if (index == realSize) {
            items[index] = value;
            realSize++;
        } else {
            System.arraycopy(items, index, items, index + 1, realSize - index);
            items[index] = value;
            realSize++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        testIndexOk(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        testIndexOk(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (index < realSize && index >= 0) {
            value = items[index];
            for (int i = (index + 1); i < realSize; i++) {
                items[i - 1] = items[i];
            }
            realSize -= 1;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index value: " + index
                    + "is greater than size(): " + size());
        }
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < realSize; i++) {
            if (items[i] == element || (items[i] != null && items[i].equals(element))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("value not found");
        } else {
            T value = items[index];
            remove(index);
            return value;
        }
    }

    @Override
    public int size() {
        return realSize;
    }

    @Override
    public boolean isEmpty() {
        return realSize == 0;
    }
}
