package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] currentList;
    private Object[] newList;

    public ArrayList() {
        size = 0;
        currentList = new Object[DEFAULT_CAPACITY];
        newList = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == currentList.length) {
            grow();
            System.arraycopy(currentList, 0, newList, 0, size);
            currentList = newList;
        }
        currentList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        if (index == size) {
            add(value);
            return;
        }
        if (size == currentList.length) {
            grow();
        }

        newList = new Object[newList.length];
        System.arraycopy(currentList, 0, newList, 0, index);
        newList[index] = value;
        System.arraycopy(currentList, index, newList, index + 1, size - index);
        size++;
        currentList = newList;
    }

    @Override
    public void addAll(List<T> list) {
        if (currentList.length < size + list.size()) {
            newList = new Object[size + list.size()];
            System.arraycopy(currentList, 0, newList, 0, size);
            currentList = newList;
        }

        for (int i = 0; i < list.size(); i++) {
            currentList[size++] = list.get(i);
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

        newList = new Object[newList.length];
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
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(currentList[i])) {
                index = i;
                break;
            }
            if (element == null && currentList[i] == null) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        newList = new Object[currentList.length + (currentList.length >> 1)];
    }

    private void indexChecking(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
