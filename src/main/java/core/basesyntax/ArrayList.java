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
        if ((index >= 0) && (index <= size)) {
            Object[] temporary = new Object[list.length];
            System.arraycopy(list, 0, temporary,0, index);
            temporary[index] = value;
            System.arraycopy(list, index, temporary,index + 1, size - index);
            list = temporary;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Your index is over the size");
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
        if ((index >= 0) && (index < size)) {
            return (T)list[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Your index is over the size");
        }
    }

    @Override
    public void set(T value, int index) {
        if ((index >= 0) && (index < size)) {
            list[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Your index is over the size");
        }
    }

    @Override
    public T remove(int index) {
        if ((index >= 0) && (index < size)) {
            T removed = (T)list[index];
            if (index == list.length - 1) {
                size--;
                return removed;
            }
            System.arraycopy(this.list, index + 1, this.list,index, size - index);
            size--;
            return removed;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Your index is over the size");
        }
    }

    @Override
    public T remove(T element) {
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if ((list[i] == element) || (list[i] != null) && (list[i].equals(element))) {
                System.arraycopy(list, i + 1, list, i, size - i);
                size--;
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("The element being requested does not exist");
        }
        return element;
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
}
