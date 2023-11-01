package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VOLUME = 10;
    private int size = 0;
    private Object[] list;

    public ArrayList() {
        list = new Object[DEFAULT_VOLUME];
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            increaseVolume();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        if (size == list.length) {
            increaseVolume();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object element = list[index];
        if (index == size - 1) {
            list[index] = null;
        } else {
            System.arraycopy(list, index + 1, list, index, size - index);
        }
        size--;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == element
                    || (list[i] != null
                    && list[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The list doesn't contain element: "
                + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public void checkIndex(int index) {
        if (index < 0
                || (index > size - 1
                && index != 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist. "
                    + "You should enter a number between 0 and "
                    + size);
        }
    }

    @Override
    public void increaseVolume() {
        Object[] newList = new Object[(int) Math.round(list.length * 1.5)];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }
}


