package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public T[] grow(T value, int index) {
        T[] newElementData = null;
        if (size < elementData.length) {
            newElementData = (T[]) new Object[elementData.length];
        }
        if (size == elementData.length) {
            newElementData = (T[]) new Object[elementData.length + elementData.length / 2];
        }
        System.arraycopy(elementData, 0, newElementData, 0, index);
        newElementData[index] = value;
        System.arraycopy(elementData, index, newElementData, index + 1, size - index);
        elementData = newElementData;
        size++;
        return elementData;
    }

    public void indexCheck(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index of value has to be more or equal 0");
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index of value has to be less then arrayList size");
        }
    }

    @Override
    public void add(T value) {
        if (size < elementData.length) {
            elementData[size] = value;
            size++;
        } else {
            grow(value, size);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            this.add(value);
            return;
        }
        indexCheck(index);
        grow(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T[] newElementData = (T[]) new Object[elementData.length];
        System.arraycopy(elementData, 0, newElementData, 0, index);
        System.arraycopy(elementData, index + 1, newElementData, index, size - index - 1);
        T value = elementData[index];
        elementData = newElementData;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                removedElement = elementData[i];
                remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("There is no such element in the ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
}
