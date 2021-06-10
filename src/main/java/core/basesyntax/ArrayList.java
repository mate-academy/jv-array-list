package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int MAX_SIZE_ARRAY = 10;
    private T[] myList;
    private int size;

    public ArrayList() {
        myList = (T[]) new Object[MAX_SIZE_ARRAY];
    }

    @Override
    public void add(T value) {
        if (size + 1 >= myList.length) {
            expandingMaxSize();
        }
        myList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size + 1 == MAX_SIZE_ARRAY) {
            expandingMaxSize();
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
        checkIndex(index + 1);
        return myList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        myList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T newTempList = myList[index];
        System.arraycopy(myList, index + 1, myList, index, size - 1 - index);
        size--;
        return newTempList;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (myList[i] == element || (myList[i] != null && myList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("List haven't this element!");
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
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index incorrect");
        }
    }

    private T[] expandingMaxSize() {
        int oldCapacity = myList.length;
        if (oldCapacity > 0) {
            int newCapacity = (oldCapacity >> 1) + oldCapacity;
            return myList = Arrays.copyOf(myList, newCapacity);
        } else {
            return myList = (T[]) new Object[MAX_SIZE_ARRAY];
        }
    }
}
