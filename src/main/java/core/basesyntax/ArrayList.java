package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private Object[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = new Object[ARRAY_CAPACITY];
        size = 0;
    }

    private void increaseCapacity() {
        T[] biggerArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(arrayList, 0, biggerArray, 0, size);
        arrayList = biggerArray;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void add(T value) {
        if (arrayList.length == size) {
            increaseCapacity();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        if (arrayList.length == index) {
            increaseCapacity();
        }
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

    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T deletedElement = (T) arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i]
                    || element.equals(arrayList[i])
                    && element != null) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
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
