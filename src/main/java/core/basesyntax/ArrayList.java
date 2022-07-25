package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_SIZE = 10;
    private int size;
    private Object[] list;

    public ArrayList() {
        list = new Object[DEFAULT_LIST_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == list.length) {
            list = increase(list);
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == list.length) {
            list = increase(list);
        }
        if (index == size) {
            add(value);
        } else {
            isValid(index);
            System.arraycopy(list, index, list,index + 1, size - index);
            list[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isValid(index);
        return (T)list[index];
    }

    @Override
    public void set(T value, int index) {
        isValid(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        isValid(index);
        T removed = (T)list[index];
        if (index != list.length - 1) {
            System.arraycopy(this.list, index + 1, this.list, index, size - index);
        }
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((list[i] == element) || (list[i] != null) && (list[i].equals(element))) {
                System.arraycopy(list, i + 1, list, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("The element being requested does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] increase(Object[] list) {
        Object[] increasedList = new Object[list.length + (list.length >> 1)];
        System.arraycopy(list, 0, increasedList, 0, list.length);
        return increasedList;
    }

    private void isValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Your index " + index
                    + " is over the size " + size);
        }
    }
}
