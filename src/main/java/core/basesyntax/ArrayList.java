package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds.");
        }
        ensureCapacity();
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
        chekIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        chekIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        chekIndex(index);
        T removedElement = elementData[index];
        System.arraycopy(elementData,index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null
                    && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void chekIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds.");
        }
    }

    private void ensureCapacity() {
        if (elementData.length == size) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, oldCapacity);
        elementData = newArray;
    }
}
