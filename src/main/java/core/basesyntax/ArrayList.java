package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ONE = 1;
    private static final double SIZE_GROWTH_INDEX = 1.5;

    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        add(value,size);
    }

    @Override
    public void add(T value, int index) {
        validateAddIndex(index);
        if (size + ONE >= elementData.length || index >= elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + ONE,
                size + ONE - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        final Object removedElement = elementData[index];
        if (index != size - ONE) {
            System.arraycopy(elementData, index + ONE, elementData, index, size - index);
        }
        elementData[size - ONE] = null;
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findElementIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the array: " + element);
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

    private Object[] grow() {
        int capacity = (int) (elementData.length * SIZE_GROWTH_INDEX);
        Object[] increasedArray = new Object[capacity];
        System.arraycopy(elementData, 0, increasedArray, 0, elementData.length);
        return increasedArray;
    }

    private int findElementIndex(T value) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == value
                    || (elementData[i] != null && elementData[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index provided");
        }
    }

    private void validateAddIndex(int index) {
        if (index > size + ONE || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index provided");
        }
    }
}
