package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private static final int INITIAL_SIZE = 0;
    private transient Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = INITIAL_SIZE;
    }

    @Override
    public void add(T value) {
        raiseIfRequirement();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        raiseIfRequirement();
        inspectIndexForAdd(index);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] additionalArray = list.toArray();
        int additionalArrayLength = additionalArray.length;
        int existingArrayLength = elementData.length;
        if (additionalArrayLength > existingArrayLength - size) {
            raise();
        }
        System.arraycopy(additionalArray, 0, elementData, size, additionalArrayLength);
        size += additionalArrayLength;
    }

    @Override
    public T get(int index) {
        inspectIndexForGet(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        inspectIndexForGet(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        inspectIndexForGet(index);
        return removeFromArray(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null
                    && element.equals(elementData[i])) {
                return removeFromArray(i);
            }
        }
        throw new NoSuchElementException("Can't find such element: \"" + element + "\"");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //private methods
    private void inspectIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Entered index " + index + " are not in range of data");
        }
    }

    private void inspectIndexForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Entered index " + index + " are not in range of data");
        }
    }

    private void raiseIfRequirement() {
        int currentCapacity = elementData.length;
        if ((MAX_CAPACITY - currentCapacity) > (currentCapacity / 2)) {
            if (currentCapacity == size) {
                raise();
            }
        } else if (currentCapacity == MAX_CAPACITY) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList reached maximum capacity");
        } else {
            raiseToMax();
        }
    }

    private void raise() {
        int currentCapacity = elementData.length;
        int raisedCapacity = currentCapacity + (currentCapacity >> 1);
        elementData = Arrays.copyOf(elementData, raisedCapacity);
    }

    private void raiseToMax() { // Be careful with that
        elementData = Arrays.copyOf(elementData, MAX_CAPACITY);
    }

    private T removeFromArray(int index) {
        T removedUnit = (T) elementData[index];
        if (index < size - 1) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        } else {
            System.arraycopy(elementData, index, elementData, index,size - index);
        }
        size--;
        return removedUnit;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = get(i);
        }
        return result;
    }
}

