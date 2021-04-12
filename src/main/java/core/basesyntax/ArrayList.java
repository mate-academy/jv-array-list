package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] objectsArray = (T[]) new Object[]{};
    private int size;

    private Object[] grow() {
        int defaultCapacity = 10;
        int oldCapacity = objectsArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (oldCapacity > 0) {
            return objectsArray = Arrays.copyOf(objectsArray, newCapacity);
        }
        return objectsArray = (T[]) new Object[Math.max(defaultCapacity, newCapacity)];
    }

    private void indexInBoundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }

    public int getIndexFromArrayOfRequiredElement(T element) {
        for (int i = 0; i < size; i++) {
            if (element == objectsArray[i] || element != null && element.equals(objectsArray[i])) {
                return i;
            }
        }
        return -1;
    }

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
            objectsArray = (T[]) grow();
        }
        if (index < size) {
            Object[] buffer = new Object[size - index];
            System.arraycopy(objectsArray, index, buffer, 0, size - index);
            objectsArray[index] = value;
            System.arraycopy(buffer, 0, objectsArray, index + 1, buffer.length);
        } else {
            objectsArray[index] = value;
        }
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
        return (T) objectsArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexInBoundsCheck(index);
        objectsArray[index] = value;
    }

    @Override
    public T remove(T element) {
        if (getIndexFromArrayOfRequiredElement(element) == -1) {
            throw new NoSuchElementException("Element does not exist.");
        }
        return remove(getIndexFromArrayOfRequiredElement(element));
    }

    @Override
    public T remove(int index) {
        indexInBoundsCheck(index);
        Object oldElement = objectsArray[index];
        System.arraycopy(objectsArray, index + 1, objectsArray,
                index, size - index - 1);
        size--;
        return (T) oldElement;
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
