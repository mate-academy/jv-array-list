package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] grow(int preferredNewCapacity) {
        int newCapacity = elementData.length + (elementData.length >> 1);
        newCapacity = Math.max(preferredNewCapacity, newCapacity);
        T[] tempElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, tempElementData, 0, size);
        elementData = tempElementData;
        return elementData;
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
        if (size == elementData.length) {
            grow(size);
        }
        System.arraycopy(elementData,index,elementData,index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() >= elementData.length - size) {
            grow(list.size() + elementData.length);
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
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
        T removeValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
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
