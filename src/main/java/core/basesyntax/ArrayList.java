package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double INCREASING_COEFFICIENT = 10;
    private static final int IMPORTANT_VALUE = 1;

    private T[] elementData = (T[]) new Object[DEFAULT_SIZE];
    private int size = 0;

    private T[] grow(int oldSize) {
        return (T[]) new Object[(int) (oldSize * INCREASING_COEFFICIENT)];
    }

    private T[] copiedList(T[] oldList, T[] newList) {
        for (int i = 0; i < oldList.length; i++) {
            newList[i] = oldList[i];
        }
        return newList;
    }

    private void addListInList(List<T> sourceList, T[] destList,
                              int sizeOfSourceList, int sizeOfDestList) {
        for (int i = sizeOfDestList, j = 0; j < sizeOfSourceList; j++, i++) {
            destList[i] = sourceList.get(j);
        }
    }

    private void droppedValue(T[] elementData, int index) {
        int numMoved = size - index - IMPORTANT_VALUE;
        System.arraycopy(elementData, index + IMPORTANT_VALUE, elementData, index, numMoved);
        elementData[size - IMPORTANT_VALUE] = null;
        size -= IMPORTANT_VALUE;
    }

    @Override
    public void add(T value) {
        if (size < elementData.length) {
            elementData[size] = value;
        } else {
            elementData = copiedList(elementData, grow(elementData.length));
            elementData[size] = value;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size + IMPORTANT_VALUE || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        if (DEFAULT_SIZE > elementData.length || size < elementData.length) {
            System.arraycopy(elementData, index, elementData,
                    index + IMPORTANT_VALUE, size - index);
            elementData[index] = value;
        } else {
            T[] widerList = copiedList(elementData, grow(elementData.length));
            System.arraycopy(elementData, index, widerList, index + IMPORTANT_VALUE, size - index);
            widerList[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int sizeOfSourceList = list.size();
        if (size + list.size() < elementData.length) {
            addListInList(list, elementData, sizeOfSourceList, size);
        } else {
            addListInList(list, grow(elementData.length), sizeOfSourceList, size);
        }
        size += sizeOfSourceList;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        T oldValue = elementData[index];
        droppedValue(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element.equals(elementData[i])) {
                index = i;
                break;
            } else if (size - IMPORTANT_VALUE == i && index == 0) {
                throw new NoSuchElementException("There is no such element present");
            }
        }
        T oldValue = elementData[index];
        droppedValue(elementData, index);
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
