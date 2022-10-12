package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final int EXTRA_NUMBER = 1;
    private static final double ENLARGE_INDEX = 1.5;
    private T value;
    private int index;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            enlarge();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        if (size == elementData.length) {
            enlarge();
        }
        for (int i = size - EXTRA_NUMBER; i >= index; i--) {
            elementData[i + EXTRA_NUMBER] = elementData[i];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((size += list.size()) >= elementData.length) {
            enlarge();
        }
        int counter = size - list.size();
        for (int i = 0; i < list.size(); i++) {
            elementData[counter] = (list.get(i));
            counter++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removed = (T) elementData[index];
        System.arraycopy(elementData,index + EXTRA_NUMBER, elementData, index,
                elementData.length - index - EXTRA_NUMBER);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no that element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == DEFAULT_SIZE;
    }

    private int checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("That index does not exist");
        }
        return index;
    }

    private void enlarge() {
        int newLength = (int) (elementData.length * ENLARGE_INDEX);
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementData,0,newArray,0,elementData.length);
        elementData = newArray;
    }
}
