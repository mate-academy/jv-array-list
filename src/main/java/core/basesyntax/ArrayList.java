package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfNeeded();
        verifyIndexForAdd(index);
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
            grow();
        }
        System.arraycopy(additionalArray, 0, elementData, size, additionalArrayLength);
        size += additionalArrayLength;
    }

    @Override
    public T get(int index) {
        verifyIndexForGet(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndexForGet(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndexForGet(index);
        return removeFromArray(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(i, element)) {
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

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size(); i++) {
            result[i] = get(i);
        }
        return result;
    }

    private void verifyIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Entered index " + index + " are not in range of data length: " + size);
        }
    }

    private void verifyIndexForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Entered index " + index + " are not in range of data");
        }
    }

    private void growIfNeeded() {
        int currentCapacity = elementData.length;
        if ((MAX_CAPACITY - currentCapacity) > (currentCapacity / 2)) {
            if (currentCapacity == size) {
                grow();
            }
        } else if (currentCapacity == MAX_CAPACITY) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList reached maximum capacity");
        } else {
            raiseToMax();
        }
    }

    private void grow() {
        int currentCapacity = elementData.length;
        int raisedCapacity = currentCapacity + (currentCapacity >> 1);
        elementData = Arrays.copyOf(elementData, raisedCapacity);
    }

    private void raiseToMax() { // Be careful with that
        elementData = Arrays.copyOf(elementData, MAX_CAPACITY);
    }

    private T removeFromArray(int index) {
        T removedUnit = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedUnit;
    }

    private boolean elementsAreEqual(int index, T element) {
        if (element == elementData[index] || element != null
                    && element.equals(elementData[index])) {
            return true;
        }
        return false;
    }
}
