package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int newCapacity;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public T[] grow(int size) {
        if (newCapacity > size) {
            newCapacity = size;
        }
        newCapacity = elementData.length + (elementData.length >> 1);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Incorrect index: " + index + ", Size: " + this.size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " does not exist.");
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow(size);
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        T[] tempElementData = (T[]) new Object[elementData.length + 1];
        if (size == elementData.length) {
            grow(size);
        }
        for (int i = 0; i < size + 1; i++) {
            if (i < index) {
                tempElementData[i] = elementData[i];
            } else if (i == index) {
                tempElementData[i] = value;
                size++;
            } else {
                tempElementData[i] = elementData[i - 1];
            }
        }
        elementData = Arrays.copyOf(tempElementData, tempElementData.length);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
            if (list.size() > elementData.length - size) {
                grow(list.size() + elementData.length);
            }
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T[] tempElementData = (T[]) new Object[elementData.length - 1];
        T removeValue = null;

        for (int i = 0, j = 0; i < elementData.length; i++) {
            if (i == index) {
                removeValue = elementData[i];
                continue;
            }
            tempElementData[j++] = elementData[i];
        }
        elementData = Arrays.copyOf(tempElementData, tempElementData.length);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is absent in the list.");
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
