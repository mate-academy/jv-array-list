package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData = {};

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        sizeCheck();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {

        if (size != 0 && (index > size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index as : " + index);
        }

        int sizeAddByIndex = size;
        size = sizeAddByIndex + 1;
        sizeCheck();
        Object[] elementDataAddByIndex = elementData;
        System.arraycopy(elementDataAddByIndex, index, elementDataAddByIndex,
                index + 1,sizeAddByIndex - index);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] listToArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = list.get(i);
        }
        int listLength = listToArray.length;
        Object[] elementDataAddAll = elementData;
        if (listLength > (elementData.length - size)) {
            elementDataAddAll = grow(size + listLength);
        }
        System.arraycopy(listToArray, 0, elementDataAddAll, size, listLength);
        elementData = elementDataAddAll;
        size = size + listLength;
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
        T removedElement = (T) elementData[index];
        shrinkArray(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        int indexToRemove = -1;

        for (int i = 0; i < elementData.length; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                removedElement = (T) elementData[i];
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            shrinkArray(indexToRemove);
        } else {
            throw new NoSuchElementException("There is no such element as: " + element);
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

    private void indexCheck(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no such index as : " + index);
        }
    }

    private void sizeCheck() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        int newIncreasedCapacity = (int) (elementData.length * 1.5);
        Object[] elementDataIncreased = new Object[newIncreasedCapacity];
        System.arraycopy(elementData, 0, elementDataIncreased, 0, size);
        elementData = elementDataIncreased;
    }

    private Object[] grow(int capacity) {
        Object[] elementDataIncreased = new Object[capacity];
        System.arraycopy(elementData, 0, elementDataIncreased, 0, size);
        return elementData = elementDataIncreased;
    }

    private void shrinkArray(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
    }
}
