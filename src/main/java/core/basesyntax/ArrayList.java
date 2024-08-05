package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        rangeCheck(index);
        return elementData(index);
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = elementData(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private T elementData(int index) {
        return (T) elementData[index];
    }
}
