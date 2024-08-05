package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double CAPACITY_INDEX = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than the "
                    + "size of the array or uncorect");
        }
        grow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = index(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list, " + element);
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
        if (elementData.length == size) {
            int newCapacity = (int) (elementData.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = (T[]) newArray;
        }
    }

    private void isIndexValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is greater than the size "
                    + "of the array or uncorrect");
        }
    }

    private int index(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }
}
