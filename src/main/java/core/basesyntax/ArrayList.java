package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_SIZE_ARRAY = 10;
    private T[] myList;
    private int size;

    public ArrayList() {
        myList = (T[]) new Object[MAX_SIZE_ARRAY];
    }

    @Override
    public void add(T value) {
        if (size >= myList.length) {
            myList = expandArray();
        }
        myList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is incorrect");
        }
        if (size == myList.length) {
            myList = expandArray();
        }
        System.arraycopy(myList, index, myList, index + 1, size++ - index);
        myList[index] = value;
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
        return myList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        myList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = myList[index];
        System.arraycopy(myList, index + 1, myList, index, size - 1 - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (myList[i] == element || (myList[i] != null && myList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("List doesn't have this element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is incorrect");
        }
    }

    private T[] expandArray() {
        int oldCapacity = myList.length;
        int newCapacity = (oldCapacity >> 1) + oldCapacity;
        T[] newMyList = (T[]) new Object[newCapacity];
        System.arraycopy(myList, 0, newMyList, 0, size);
        return newMyList;
    }
}
