package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private int size;
    private T[] listOfElements;

    public ArrayList(int s) {
        if (s <= 0) {
            throw new IllegalArgumentException("the size of the array cannot be below zero");
        }
        listOfElements = (T[]) new Object[s];
    }

    public ArrayList() {
        listOfElements = (T[]) new Object[DEFAULT_SIZE_OF_ARRAY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        if (size < listOfElements.length) {
            listOfElements[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("this index is out of range of the array");
        }
        ensureCapacity();
        System.arraycopy(listOfElements, index, listOfElements, index + 1, size - index);
        listOfElements[index] = value;
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is out of range of the array");
        }
        return listOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkExceptions(index);
        listOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkExceptions(index);
        T removeElement = listOfElements[index];
        System.arraycopy(listOfElements, index + 1, listOfElements, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == listOfElements[i]
                    || element != null && element.equals(listOfElements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("this element is not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkExceptions(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is out of range of the array");
        }
    }

    private void ensureCapacity() {
        if (size == listOfElements.length) {
            T[] newArr = (T[]) new Object[listOfElements.length + (listOfElements.length >> 1)];
            System.arraycopy(listOfElements, 0, newArr, 0, size);
            listOfElements = newArr;
        }
    }
}
