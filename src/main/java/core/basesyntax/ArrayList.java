package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] myList;

    ArrayList() {
        myList = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (myList.length == size) {
            myList = resize(myList);
        }
        myList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (isInsideOfArray(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " outside ArrayList");
        } else {
            if (isArrayFilled()) {
                myList = resize(myList);
            }
            T[] boof = (T[]) new Object[myList.length];
            System.arraycopy(myList, 0, boof, 0, index);
            boof[index] = value;
            System.arraycopy(myList, index, boof, index + 1, myList.length - index - 1);
            myList = boof;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = size,k = 0;k < list.size();i++,k++) {
            if (isArrayFilled()) {
                myList = resize(myList);

            }
            myList[i] = list.get(k);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (isInsideOfArray(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " outside ArrayList");
        } else {
            return myList[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (isInsideOfArray(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " outside ArrayList");
        }
        myList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isInsideOfArray(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " outside ArrayList");
        }
        T[] boof = (T[]) new Object[myList.length];
        T boofer;
        boofer = myList[index];
        System.arraycopy(myList,0,boof,0,index);
        System.arraycopy(myList,index + 1,boof,index,myList.length - index - 1);
        myList = boof;
        size--;
        return boofer;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((myList[i] == element) || (myList[i] != null && myList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private T[] resize(T[] myList) {
        T[] resizeList = (T[])new Object[myList.length + myList.length / 2];
        System.arraycopy(myList,0,resizeList,0,myList.length);
        return resizeList;

    }

    private boolean isArrayFilled() {
        return myList.length == size;
    }

    private boolean isInsideOfArray(int index) {
        return index < 0 || index >= size;

    }
}
