package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_SIZE = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        this.list = (T[]) new Object[DEFAULT_LIST_SIZE];
        this.size = 0;
    }

    private void listIncrease() {
        T[] newList = (T[]) new Object[(int) (list.length * 1.5)];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
    }

    private void arrayIndexOutOfBoundException(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
    }

    private void isListFull() {
        if (size == list.length) {
            listIncrease();
        }
    }

    @Override
    public void add(T value) {
        isListFull();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == list.length) {
            isListFull();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;
        arrayIndexOutOfBoundException(index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        arrayIndexOutOfBoundException(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        arrayIndexOutOfBoundException(index);
        if (index < size) {
            list[index] = value;
        } else {
            add(value);
        }
    }

    @Override
    public T remove(int index) {
        arrayIndexOutOfBoundException(index);
        T remove = get(index);
        System.arraycopy(list, index + 1, list, index, size - index);
        size--;
        return remove;
    }

    @Override
    public T remove(T t) {
        int index = -1;
        if (t == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if ((t.equals(list[i]) || t == list[i])) {
                index = i;
            }
        }
        if (index != -1) {
            return remove(index);
        }
        throw new NoSuchElementException("Element isn't found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
