package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int BASE_CAPACITY = 10;
    public static final int GROWTH_SHIFT_AMOUNT = 1;
    private Object[] objectData;
    private int size;

    public ArrayList() {
        objectData = new Object[BASE_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        checkCapacityForAdd(1);
        objectData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidationForAdd(index);
        checkCapacityForAdd(1);
        System.arraycopy(objectData, index, objectData, index + 1, size - index);
        objectData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        checkCapacityForAdd(list.size());
        for (int i = 0; i < list.size(); i++) {
            T valueToCopy = (T) list.get(i);
            objectData[size + i] = valueToCopy;
        }
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return (T) objectData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        objectData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        final T removedEl = (T) objectData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(objectData, index + 1, objectData, index, numMoved);
        }
        size--;
        objectData[size] = null;
        return removedEl;
    }

    @Override
    public T remove(T element) {
        int index = findIndexOfElement(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element in this array");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacityForAdd(int addCount) {
        int requiredCapacity = size + addCount;
        if (requiredCapacity <= objectData.length) {
            return;
        }
        int oldCapacity = objectData.length;
        int newCapacity = oldCapacity + (oldCapacity >> GROWTH_SHIFT_AMOUNT);
        if (newCapacity < requiredCapacity) {
            newCapacity = requiredCapacity;
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(objectData, 0, newArray, 0, size);
        objectData = newArray;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
        }
    }

    private void indexValidationForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds for add: "
                    + index + ", size: " + size);
        }
    }

    private int findIndexOfElement(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (objectData[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(objectData[i])) {
                return i;
            }
        }

        return -1;
    }
}
