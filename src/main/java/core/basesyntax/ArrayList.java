package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesn't exists in arraylist: " + index);
        }
        grow();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        grow();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(size, index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(size, index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(size, index);
        T previosNumber = elements[index];
        int numMove = size - index - 1;
        if (numMove > 0) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        return previosNumber;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element does not exist.");
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
        if (size == elements.length) {
            int newCapacity = elements.length + (elements.length >> 1);
            T[] newArray = (T[])new Object[newCapacity];
            System.arraycopy(elements,0,newArray,0,size);
            elements = newArray;
        }
    }

    public void checkIndex(int size,int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesn't exists in arraylist: " + index);
        }
    }
}
