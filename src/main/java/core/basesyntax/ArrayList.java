package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    private T[] objectsArray = (T[]) new Object[]{};
    private int size;
    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (index == objectsArray.length) {
            grow();
        }
        if (index < size) {
            System.arraycopy(objectsArray, index, objectsArray, index + 1, size - index);
        }
        objectsArray[index] = value;
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
        indexInBoundsCheck(index);
        return objectsArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexInBoundsCheck(index);
        objectsArray[index] = value;
    }

    @Override
    public T remove(T element) {
        if (getElementIndex(element) == -1) {
            throw new NoSuchElementException("Element does not exist.");
        }
        return remove(getElementIndex(element));
    }

    @Override
    public T remove(int index) {
        indexInBoundsCheck(index);
        T oldElement = objectsArray[index];
        System.arraycopy(objectsArray, index + 1, objectsArray,
                index, size - index);
        size--;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int oldCapacity = objectsArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        objectsArray = Arrays.copyOf(objectsArray, Math.max(DEFAULT_ARRAY_CAPACITY, newCapacity));
    }

    private void indexInBoundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    private int getElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == objectsArray[i] || element != null && element.equals(objectsArray[i])) {
                return i;
            }
        }
        return -1;
    }
}
