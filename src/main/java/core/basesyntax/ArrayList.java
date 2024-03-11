package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
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
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
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
        checkSize(size, index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkSize(size, index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkSize(size, index);
        T previosNumber = elements[index];
        int numMove = size - index - 1;
        if (numMove > 0) {
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
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
            T[] newArray =(T[]) new Object[newCapacity];
            System.arraycopy(elements,0,newArray,0,size);
            elements = newArray;
        }
    }

    public void checkSize(int size,int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesn't exists in arraylist: " + index);
        }
    }
}
