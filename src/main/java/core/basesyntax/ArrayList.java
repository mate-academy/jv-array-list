package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity = DEFAULT_CAPACITY;
    private T[] list;

    public ArrayList() {
        list = (T[]) new Object[capacity];
    }

    public void checkingIndex(int incomingIndex) {
        if (incomingIndex < 0 || incomingIndex >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can not find index : " + incomingIndex);
        }
    }

    public void growCapacity() {
        capacity += capacity >> 1;
        list = Arrays.copyOf(list,capacity);
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can not find index : " + index);
        }
        if (size == capacity) {
            growCapacity();
        }
        System.arraycopy(list, index, list,index + 1, size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> incominglist) {
        for (int i = 0; i < incominglist.size(); i++) {
            add(incominglist.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkingIndex(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndex(index);
        T removedElement = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || element != null && element.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can not find element : " + element);
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
