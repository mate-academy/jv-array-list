package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private T[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkElementDataLength();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        checkElementDataLength();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final int index = findElement(element);
        T oldValue = elementData[index];
        fastRemove(index);
        return oldValue;
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
    public String toString() {
        return Arrays.toString(elementData);
    }

    @SuppressWarnings("unchecked")
    private void increaseElementDataLength() {
        int newCapacity = (int) (elementData.length * CAPACITY_MULTIPLIER);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void checkIndexForAdd(int index) {
        indexValidation(index);
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of size range");
        }
    }

    private void checkIndex(int index) {
        indexValidation(index);
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no value on index: "
                    + index);
        }
    }

    private void indexValidation(int index){
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void fastRemove(int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData, index + 1,
                    elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;
    }

    private int findElement(T object) {
        for (int i = 0; i < elementData.length; i++) {
            if (object == elementData[i] || object != null
                    && object.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Element: " + object + " is not found");
    }

    private void checkElementDataLength() {
        if (size == elementData.length) {
            increaseElementDataLength();
        }
    }
}
