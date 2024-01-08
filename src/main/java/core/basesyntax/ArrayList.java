package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private T[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * 1.5);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private T[] grow(int capacity) {
        return elementData = Arrays.copyOf(elementData, elementData.length + capacity);
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element by this index!");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Can't add null List to ArrayList");
        }
        int freeMemory = elementData.length - size;
        if (freeMemory < list.size()) {
            elementData = grow(list.size() - freeMemory);
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[i + size] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element at the specified index");
        } else {
            return elementData[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element at the specified index");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element at the specified index");
        }
        T removeElement = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                T removeElement = elementData[i];
                for (int j = i; j < size - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                size--;
                return removeElement;
            }
        }
        throw new NoSuchElementException("Can't find element");
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
