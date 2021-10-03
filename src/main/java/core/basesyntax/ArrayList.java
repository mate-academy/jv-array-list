package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] list = (T[]) new Object[DEFAULT_CAPACITY];
    private int capacity = DEFAULT_CAPACITY;
    private int size = 0;

    private void grow() {
        int oldCapacity = capacity;
        capacity = (int) (capacity * 1.5);
        T[] growList = (T[]) new Object[capacity];
        System.arraycopy(list, 0, growList, 0, oldCapacity);
        list = growList;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            grow();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index to add value!");
        }
        if (capacity == size) {
            grow();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > capacity) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index to get value!");
        }
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index to remove value!");
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedElement;
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index to remove value!");
        }
        removedElement = list[index];
        System.arraycopy(list, index + 1, list, index,size - 1 - index);
        list[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int ind = 0; ind < size; ind++) {
            T el = list[ind];
            if (el == element || (el != null && el.equals(element))) {
                return remove(ind);
            }
        }
        throw new NoSuchElementException("Wrong index to remove value!");
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
