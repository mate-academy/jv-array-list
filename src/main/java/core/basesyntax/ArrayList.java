package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object [] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementData = growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        elementData = growIfArrayFull();
        System.arraycopy(elementData,index,elementData,index + 1,elementData.length - (index + 1));
        elementData[index] = value;

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        elementData = growIfArrayFull();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removed = (T) elementData[index];
        System.arraycopy(elementData,index + 1,elementData,index,size - 1 - index);
        elementData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " not present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    private Object[] growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int)(size * 1.5);
            Object [] newElementData = new Object[newCapacity];
            System.arraycopy(elementData,0,newElementData,0,size);
            return newElementData;
        }
        return elementData;
    }
}
