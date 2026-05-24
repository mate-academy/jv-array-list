package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_ARRAY_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        elementData = checkCapacity(elementData);
        elementData[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
        elementData = checkCapacity(elementData);
        System.arraycopy(elementData, index, elementData, index + 1, size() - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            return;
        }
        Object[] arrayFromCollection = getArrayFromCollection(list);
        int capacity = list.size() + size();
        Object[] summaryArray = new Object[capacity];
        System.arraycopy(elementData, 0, summaryArray, 0, size());
        System.arraycopy(arrayFromCollection, 0, summaryArray, size(), list.size());
        elementData = checkCapacity(summaryArray);
        size += list.size();
    }

    @Override
    public T get(int index) {
        boolean rightIndex = validateIndex(index);
        if (rightIndex == true) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public void set(T value, int index) {
        boolean rightIndex = validateIndex(index);
        if (rightIndex == true) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    @Override
    public T remove(int index) {
        T deletedElement;
        boolean rigthIndex = validateIndex(index);
        if (rigthIndex == true) {
            deletedElement = (T) elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size() - index - 1);
            elementData[size - 1] = null;
            size--;
            return deletedElement;
        }
        throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if ((element == null && elementData[i] == null)
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element founded in this List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public Object[] checkCapacity(Object[] elementData) {
        if (size() >= elementData.length) {
            elementData = getNewCapacityArray(elementData);
            return elementData;
        }
        return elementData;
    }

    public boolean validateIndex(int index) {
        if (index < size() && index >= 0) /*&& elementData[index] != null)*/ {
            return true;
        }
        return false;
    }

    public Object[] getArrayFromCollection(List<T> list) {
        Object[] arrayFromCollection = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayFromCollection[i] = list.get(i);
        }
        return arrayFromCollection;
    }

    public Object[] getNewCapacityArray(Object[] elementData) {
        int capacityArray = elementData.length;
        capacityArray += (capacityArray >> 1);
        Object[] newArray = new Object[capacityArray];
        System.arraycopy(elementData, 0, newArray, 0, size());
        return newArray;
    }
}
