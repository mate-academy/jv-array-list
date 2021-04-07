package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final int FIRST_INDEX_OF_ARRAY = 0;
    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS_EXCEPTION
            = "The index passed to the method is invalid";
    private static final String MESSAGE_NO_SUCH_ELEMENT_EXCEPTION
            = "There is no such element in the list";
    private int capacity;
    private T[] elementData;

    public ArrayList() {
        capacity = FIRST_INDEX_OF_ARRAY;
        elementData = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        if (capacity == elementData.length) {
            elementData = resizeArray();
        }
        elementData[capacity] = value;
        capacity++;
    }

    @Override
    public void add(T value, int index) {
        if (capacity == elementData.length) {
            elementData = resizeArray();
        }
        if (index > capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS_EXCEPTION);
        }
        System.arraycopy(elementData, index, elementData, index + 1, capacity - index);
        elementData[index] = value;
        capacity++;
    }

    private T[] resizeArray() {
        T[] enlargedArray = (T[]) new Object[(capacity * 3) / 2 + 1];
        System.arraycopy(elementData, 0, enlargedArray, 0, capacity);
        return enlargedArray;
    }

    @Override
    public void addAll(List<T> list) {
        if (capacity + list.size() > elementData.length) {
            elementData = resizeArrayForListSize(list);
        }
        T[] arrayFromIncomeList = listToArray(list);
        System.arraycopy(arrayFromIncomeList, FIRST_INDEX_OF_ARRAY, elementData,
                capacity, arrayFromIncomeList.length);
        capacity += arrayFromIncomeList.length;
    }

    private T[] listToArray(List<T> list) {
        T[] resultArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resultArray[i] = list.get(i);
        }
        return resultArray;
    }

    private T[] resizeArrayForListSize(List<T> list) {
        T[] enlargedArray = (T[]) new Object[capacity + list.size()];
        System.arraycopy(elementData, 0, enlargedArray, 0, capacity);
        return enlargedArray;
    }

    @Override
    public T get(int index) {
        if (index >= capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS_EXCEPTION);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS_EXCEPTION);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > capacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS_EXCEPTION);
        }
        T deletedItem = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, capacity - index);
        capacity--;
        return deletedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (equalsItems(element, elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(MESSAGE_NO_SUCH_ELEMENT_EXCEPTION);
    }

    private boolean equalsItems(T newItem, T itemInList) {
        return (newItem == itemInList || newItem != null && newItem.equals(itemInList));
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return capacity > 0 ? false : true;
    }
}
