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

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
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
        isListFull();
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
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == list[i] || (t != null && t.equals(list[i]))) {
                return remove(i);
            }
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
