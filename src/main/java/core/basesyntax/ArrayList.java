package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_ITEMS = 10;
    private static final double GROW_INDEX = 1.5;
    private int size;
    private T[] arrayList;

    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_LIST_ITEMS];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index "
                    + index + " is out of bounds");
        }
        checkCapacity();
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public void checkCapacity() {
        if (size == arrayList.length) {
            int capacity = (int) (arrayList.length * GROW_INDEX);
            Object[] biggerArray = new Object[capacity];
            System.arraycopy(arrayList, 0, biggerArray, 0, size);
            arrayList = (T[]) biggerArray;
        }
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBoundsException(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBoundsException(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBoundsException(index);
        T newArray = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return newArray;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i] == element || (arrayList[i] != null && arrayList[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no " + element + " to remove");
    }

    public void checkIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index "
                    + index + " is out of bounds");
        }
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
