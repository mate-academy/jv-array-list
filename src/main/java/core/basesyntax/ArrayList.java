package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] myList = (T[]) new Object[DEFAULT_SIZE];
    private int size;

    public T newSizeArrayList() {
        if (size >= myList.length) {
            myList = Arrays.copyOf(myList, (int) (myList.length * 1.5));
        }
        return (T) myList;
    }

    public void chekForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        newSizeArrayList();
        myList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        chekForAdd(index);
        if (size >= myList.length) {
            newSizeArrayList();
        }
        System.arraycopy(myList, index, myList, index + 1, size - index);
        myList[index] = value;
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
        chekForAdd(index);
        if (index + 1 > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return myList[index];
    }

    @Override
    public void set(T value, int index) {
        chekForAdd(index);
        if (index + 1 > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        myList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removeElement = myList[index];
        int shift = size - index - 1;
        System.arraycopy(myList, index + 1, myList, index, shift);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < myList.length; i++) {
            if (Objects.equals(element, myList[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This array list sheet does not contain an element "
                + element);
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
