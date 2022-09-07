package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        grow();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newArrayList = new Object[elements.length + list.size()];
        System.arraycopy(elements, 0, newArrayList, 0, size);
        System.arraycopy(list, 0, newArrayList, size + 1, list.size());
        elements = newArrayList;
        size += list.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index " + index + " doesn't exist");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index " + index + " doesn't exist");
        }
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index " + index + " doesn't exist");
        }
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(T element) {
        T removedElement = null;
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) { todo: //How to remove null element
                removedElement = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                break;
            }
            if (i == (size - 1) && (!elements[i].equals(element))) {
                throw new NoSuchElementException();
            }
        }
        return removedElement;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (size == elements.length) {
            Object[] newArray = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Such index " + index + " doesn't exist");
        }
    }
}