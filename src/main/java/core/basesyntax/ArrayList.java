package core.basesyntax;

import static java.lang.System.arraycopy;

public class ArrayList<T> implements List {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    private Object[] grow() {
        T[] newElementData = (T[]) new Object[elementData.length];
        arraycopy(elementData, 0, newElementData, 0, elementData.length);
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        elementData = (T[]) new Object[newCapacity];
        arraycopy(newElementData, 0, elementData, 0, newElementData.length);
        return elementData;
    }

    @Override
    public void add(Object value) {
        if (size + 1 == elementData.length) {
            elementData = (T[]) grow();
        }
        elementData[size] = (T) value;
        size++;
    }

    @Override
    public void add(Object value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }

        if (size + 1 == elementData.length) {
            elementData = (T[]) grow();
        }
        Object[] temporaryArray = new Object[elementData.length];
        Object[] helper = new Object[temporaryArray.length];

        arraycopy(elementData, 0, temporaryArray, 0, elementData.length);

        arraycopy(temporaryArray, 0, helper, 0, temporaryArray.length - index);
        helper[index] = value;
        arraycopy(temporaryArray, index, helper, index + 1, temporaryArray.length - index - 1);

        elementData = (T[]) new Object[helper.length];
        arraycopy(helper, 0, elementData, 0, helper.length);
        size++;
    }

    @Override
    public void addAll(List list) {
        T[] listToArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = (T) list.get(i);
        }
        int listLength = listToArray.length;
        while (size + listToArray.length > elementData.length) {
            elementData = (T[]) grow();
        }
        arraycopy(listToArray, 0, elementData, size, listLength);
        size += listLength;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(Object value, int index) {
        if (index >= 0 && index <= size - 1) {
            elementData[index] = (T) value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        } else {
            T[] temporaryArrayBefore = (T[]) new Object[size - (size - index)];
            T[] temporaryArrayAfter = (T[]) new Object[size - index];
            arraycopy(elementData, 0, temporaryArrayBefore,
                    0, temporaryArrayBefore.length);
            arraycopy(elementData, index + 1, temporaryArrayAfter,
                    0, temporaryArrayAfter.length);
            elementData = (T[]) new Object[elementData.length];
            arraycopy(temporaryArrayBefore, 0, elementData,
                    0, temporaryArrayBefore.length);
            arraycopy(temporaryArrayAfter, 0,
                    elementData, index, temporaryArrayAfter.length);
            size--;
            return elementData;
        }
    }

    @Override
    public Object remove(Object element) {
        boolean isContain = false;
        int searchedIndex = 0;
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                searchedIndex = i;
                isContain = true;
                break;
            }
        }
        if (!isContain) {
            throw new NoSuchElementException("There is no such element present");
        }

        if (isContain) {

            Object[] temporaryArrayBefore = new Object[size - (size - searchedIndex)];
            Object[] temporaryArrayAfter = new Object[size - searchedIndex];
            arraycopy(elementData, 0, temporaryArrayBefore,
                    0, temporaryArrayBefore.length);
            arraycopy(elementData, searchedIndex + 1, temporaryArrayAfter,
                    0, temporaryArrayAfter.length);
            elementData = (T[]) new Object[elementData.length];
            arraycopy(temporaryArrayBefore, 0, elementData,
                    0, temporaryArrayBefore.length);
            arraycopy(temporaryArrayAfter, 0, elementData,
                    searchedIndex, temporaryArrayAfter.length);
            size--;

        }
        return elementData;
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
