package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH_ARRAY = 10;
    private static final double GROWTH_SIZE = 1.5;
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
        if (index == size) {
            add(value);
            return;
        }
        checkIndexInSize(index);
        if (size == capacity) {
            growCapacity();
        }
        Object[] tempArrayList = arrayList;
        System.arraycopy(tempArrayList, index, arrayList, index + 1, size - index);
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
            add(list.get(i));
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
        for (int i = 0; i < size; i++) {
            if (equality((T) arrayList[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element " + element + " not found.");
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
        capacity = (int) (capacity * GROWTH_SIZE);
        arrayList = new Object[capacity];
        System.arraycopy(tempArray, 0, arrayList, 0, size);
    }

    private boolean equality(T elementOne, T elementSecond) {
        return elementOne == elementSecond
                || elementOne != null && elementOne.equals(elementSecond);
    }

    private void checkIndexInSize(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index " + index + " is not included in the arrayList size.");
        }
    }
}
