package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        elementData = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (isFull()) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int numberOfValuesToAdd = list.size();
        Object[] valuesToAdd = convertListToArray(list);
        if (numberOfValuesToAdd == 0) {
            return;
        }
        if (numberOfValuesToAdd > elementData.length - size) {
            elementData = grow(size + numberOfValuesToAdd);
            System.arraycopy(valuesToAdd, 0, elementData, size, numberOfValuesToAdd);
        }
        size += numberOfValuesToAdd;
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
        T oldElement = get(index);
        internalRemove(index);
        return oldElement;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = findElement(element);
        return remove(indexOfElement);
    }

    private void internalRemove(int index) {
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData,index + 1, elementData, index, newSize);
        }
        size = newSize;
        elementData[size] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(int requiredCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < requiredCapacity) {
                newCapacity = requiredCapacity;
            }
            return Arrays.copyOf(elementData, newCapacity);
        } else {
            return new Object[Math.max(DEFAULT_CAPACITY, requiredCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is out of bounds of array");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " is out of bounds of array");
        }
    }

    private boolean isFull() {
        return size == elementData.length;
    }

    private int findElement(T elementToFind) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementToFind, elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("There is no " + elementToFind + " in the List");
    }

    private Object[] convertListToArray(List<T> list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
