package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        resizeIfNeeded();
        System.arraycopy(list,index,list,index + 1,size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0;i < list.size();i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = list[index];
        System.arraycopy(list,index + 1,list,index,size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = elementCheck(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element");
        }
        T removedElement = list[index];
        System.arraycopy(list, index + 1,list,index,size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resizeIfNeeded() {
        if (list.length == size) {
            int oldCapacity = list.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(list, 0, newArray, 0, size);
            list = (T[]) newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
        }
    }

    private int elementCheck(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].equals(element) || list[i] == element) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
        }
    }
}
