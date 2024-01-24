package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index, true);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (listSize > elementData.length - size) {
            grow(size + listSize);
        }
        Object[] newArrayForList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArrayForList[i] = list.get(i);
        }
        System.arraycopy(newArrayForList, 0, elementData, size, list.size());
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        indexCheck(index, false);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index, false);
        if (size == elementData.length) {
            grow();
        }
        for (int i = 0; i < elementData.length; i++) {
            if (i == index) {
                elementData[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        indexCheck(index, false);
        T removedObject = (T) elementData[index];
        int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        elementData[size = newSize] = null;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element to remove");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void indexCheck(int index, boolean forAdd) {
        if ((((forAdd && index > size) || (!forAdd && index >= size)) && size != 0) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] grownArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, grownArray, 0, elementData.length);
        elementData = grownArray;
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = minCapacity + (oldCapacity >> 1);
        Object[] grownArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, grownArray, 0, elementData.length);
        elementData = grownArray;
    }
}
