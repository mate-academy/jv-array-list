package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CHANGES = 1.5;
    private int sizeThatChanges = 10;
    private int size = 0;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[sizeThatChanges];
    }

    @Override
    public void add(T value) {
        resize();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        resize();
        if (index == size) {
            items[index] = value;
            size++;
        } else {
            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = value;
            size++;
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
        T value = null;
        if (index < size && index >= 0) {
            value = items[index];
            //  for (int i = (index + 1); i < size; i++) {
            //      items[i - 1] = items[i];}
            System.arraycopy(items, index + 1, items, index, size - index - 1);
            size--;
        } else {
            throw new ArrayListIndexOutOfBoundsException("index value: " + index
                    + "is greater than size: " + size);
        }
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (items[i] == element || (items[i] != null && items[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("value not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size >= sizeThatChanges) {
            sizeThatChanges *= CHANGES;
            T[] newItems = (T[]) new Object[sizeThatChanges];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is invalid: " + index);
        }
    }
}
