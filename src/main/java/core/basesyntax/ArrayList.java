package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] currentList;

    public ArrayList() {
        currentList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        currentList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexChecking(index);
        if (size == currentList.length) {
            growIfArrayFull();
        }

        Object[] newList = new Object[currentList.length];
        System.arraycopy(currentList, 0, newList, 0, index);
        newList[index] = value;
        System.arraycopy(currentList, index, newList, index + 1, size - index);
        size++;
        currentList = newList;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexChecking(index);
        return (T) currentList[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        currentList[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecking(index);

        Object[] newList = new Object[currentList.length];
        System.arraycopy(currentList, 0, newList, 0, index);
        System.arraycopy(currentList, index + 1, newList, index, size - index - 1);
        newList[size - 1] = null;
        size--;
        T elementByIndex = (T) currentList[index];
        currentList = newList;

        return elementByIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(currentList[i])) {
                return remove(i);
            }
            if (element == null && currentList[i] == null) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == currentList.length) {
            Object[] newList = new Object[currentList.length + (currentList.length >> 1)];
            System.arraycopy(currentList, 0, newList, 0, size);
            currentList = newList;
        }
    }

    private void indexChecking(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
