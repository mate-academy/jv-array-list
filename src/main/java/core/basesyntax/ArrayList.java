package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_EMPTY_CAPACITY = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private final T[] defaultEmptyArray;
    private T[] elementData;

    public ArrayList() {
        defaultEmptyArray = (T[]) new Object[DEFAULT_EMPTY_CAPACITY];
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        T[] oldElementData = elementData;
        if (elementData.length == size) {
            elementData = (T[]) new Object[newCapacity];
        }
        System.arraycopy(oldElementData, 0, elementData, 0, oldElementData.length);
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of Bounds");
        }

        if (size == elementData.length) {
            grow();
        }

        System.arraycopy(elementData, 0, elementData, 0, index);
        System.arraycopy(elementData, index, elementData, index + 1,
                elementData.length - (index + 1));
        elementData[index] = value;
        size++;
        System.out.println(Arrays.toString(elementData));
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == elementData.length) {
                grow();
            }
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of Bounds");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T value;
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of Bounds");
        }
        value = elementData[index];
        System.arraycopy(elementData, 0, elementData,
                0, index);
        System.arraycopy(elementData, index + 1, elementData, index,
                elementData.length - (index + 1));
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        T value;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                value = elementData[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException();
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
