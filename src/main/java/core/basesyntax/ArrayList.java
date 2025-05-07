package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private static final float GROW_MULTIPLIER = 1.5f;
    private Object[] elementData;
    private int size;

    public ArrayList() { // Constructor
        elementData = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacityInternal(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int oldElementDataSize = size;
        ensureCapacityInternal(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            elementData[oldElementDataSize++] = list.get(i);
        }
        size = size + list.size();
    }

    //////// Methods for addition

    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity > elementData.length) {
            long potentialSize = (long) (size * GROW_MULTIPLIER);
            if (potentialSize < MAX_ARRAY_SIZE) {
                grow(minCapacity);
            } else {
                throw new ArrayListIndexOutOfBoundsException("Addition of "
                        + minCapacity + " is to large to perform.");
            }
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (MAX_ARRAY_SIZE - minCapacity < elementData.length) {
            throw new ArrayListIndexOutOfBoundsException("Addition of "
                    + minCapacity + " is to large to perform.");
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        newCapacity = Math.max(minCapacity, newCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /////////////// GET - SET //////////////

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /////////////////////// REMOVE //////////////////////////////////////

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = (T) elementData[index];
        int numOfElementsMoved = size - index - 1;
        if (numOfElementsMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numOfElementsMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        boolean success = false;
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        if (!success) {
            throw new NoSuchElementException("Element" + element + "is not present in ArrayList");
        }
        return null;
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
