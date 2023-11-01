package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VOLUME = 10;
    private static double INCREASING_MULTIPLICATOR = 1.5;
    private int listSize = 0;
    private Object[] list;

    public ArrayList() {
        list = new Object[DEFAULT_VOLUME];
    }

    public ArrayList(int size) {
        list = new Object[size];
    }

    @Override
    public void add(T value) {
        if (listSize == list.length) {
            increaseVolume((int) Math.round(list.length * INCREASING_MULTIPLICATOR));
        }
        list[size()] = value;
        listSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size()) {
            checkIndex(index);
        }
        increaseVolume(list.length + 1);
        for (int i = size() - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = value;
        listSize++;
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
        for (int i = index; i < listSize - 1; i++) {
            list[i] = list[i + 1];
        }
        listSize--;
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
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize < 1;
    }

    @Override
    public void checkIndex(int index) {
        if (index < 0
                || (index > size() - 1
                && index != 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist. "
                    + "You should enter a number between 0 and "
                    + size());
        }
    }

    @Override
    public void increaseVolume(int newVolume) {
        Object[] newList = new Object[newVolume];
        System.arraycopy(list, 0, newList, 0, size());
        list = newList;
    }
}


