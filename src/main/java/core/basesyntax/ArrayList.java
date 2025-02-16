package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH_ARRAY = 10;
    private int capacity;
    private int size;
    private Object[] arrayList;

    public ArrayList() {
        capacity = START_LENGTH_ARRAY;
        arrayList = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            growCapacity();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is not included in the arrayList size.");
        }
        if (size == capacity) {
            growCapacity();
        }
        if (index < size) {
            Object[] tempArrayList = arrayList;
            System.arraycopy(tempArrayList, index, arrayList, index + 1, size - index);
        }
        arrayList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int totalSize = size + list.size();
        while (totalSize >= capacity) {
            growCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            arrayList[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexInSize(index);
        return (T) arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInSize(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInSize(index);
        T removeValue = (T) arrayList[index];
        Object[] tempArrayList = arrayList;
        System.arraycopy(tempArrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        for (int i = 0; i < size; i++) {
            if (equality((T) arrayList[i], element)) {
                removedElement = (T) arrayList[i];
                Object[] tempArrayList = arrayList;
                System.arraycopy(tempArrayList, i + 1, arrayList, i, size - i - 1);
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("The entered element is missing.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacity() {
        Object[] tempArray = arrayList;
        capacity = (int) (capacity * 1.5);
        arrayList = new Object[capacity];
        for (int i = 0; i < tempArray.length; i++) {
            arrayList[i] = tempArray[i];
        }
    }

    private boolean equality(T elementOne, T elementSecond) {
        return elementOne == elementSecond
                || elementOne != null && elementOne.equals(elementSecond);
    }

    private void checkIndexInSize(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is not included in the arrayList size.");
        }
    }
}
