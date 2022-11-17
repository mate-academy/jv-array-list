package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = EMPTY_ELEMENT_DATA;
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        int countElementsAfterIndex = size - index;
        Object[] elementData;
        if (size == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                countElementsAfterIndex);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int amountListElements = list.size();
        Object [] arrayFromList = toArray(list, amountListElements);
        Object[] elementData;
        final int s;
        if (amountListElements > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + amountListElements);
        }
        System.arraycopy(arrayFromList, 0, elementData, s, amountListElements);
        size = s + amountListElements;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        int countElementsAfterIndex = elementData.length - 1 - index;
        Object removedElement = elementData[index];
        System.arraycopy(elementData,index + 1,
                elementData,index,countElementsAfterIndex);
        size -= 1;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        int elementIndex = findElementIndex(element);
        return remove(elementIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != EMPTY_ELEMENT_DATA) {
            int newCapacity = (int) (oldCapacity * 1.5);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is invalid");
        }
    }

    private Object[] toArray(List<T> list, int arrayLength) {
        Object [] arrayFromList = new Object[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arrayFromList[i] = list.get(i);
        }
        return arrayFromList;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is invalid");
        }
    }

    private int findElementIndex(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element != null && elementData[i] != null && element.equals(elementData[i])) {
                return i;
            } else if (element == null && elementData[i] == null) {
                return i;
            }
        }
        throw new NoSuchElementException("Element does not exist");
    }
}
