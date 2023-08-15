package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int index;
    private Object [] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        elementData = growIfArrayFull();
        elementData[index] = value;
        index++;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        elementData = growIfArrayFull();
        Object [] newElementData = new Object[elementData.length];
        System.arraycopy(elementData,index,newElementData,0,elementData.length - index);
        elementData[index] = value;
        System.arraycopy(newElementData,0,elementData,
                index + 1,newElementData.length - (index + 1));
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
        return (T) minusElementInArray(elementData,index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return (T) minusElementInArray(elementData,i);
            }
        }
        throw new NoSuchElementException("Element not present");
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
        if (index >= 0 && index < size) {
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is invalid");
    }

    private Object minusElementInArray(Object[] elementData, int index) {
        Object [] newElementData = new Object[elementData.length];
        System.arraycopy(elementData,index + 1,newElementData,0,elementData.length - (index + 1));
        Object element = elementData[index];
        System.arraycopy(newElementData,0,elementData,index,elementData.length - index);
        size -= 1;
        return element;
    }

    private Object[] growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int)(size * 1.5);
            return Arrays.copyOf(elementData,newCapacity);
        }
        return elementData;
    }
}
