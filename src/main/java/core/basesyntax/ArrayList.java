package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity = DEFAULT_CAPACITY;
    private T[] elements = (T[]) new Object[DEFAULT_CAPACITY];
    private int currentSize;

    @Override
    public void add(T value) {
        if (currentSize >= currentCapacity) {
            increaseCapacity();
        }
        elements[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to index " + index);
        }
        if (currentSize == currentCapacity) {
            increaseCapacity();
        }

        // first, I move the existing elements by one position starting from new index
        for (int i = currentSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        // then I change the value
        elements[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        while (currentCapacity < currentSize + list.size()) {
            increaseCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            elements[i + currentSize] = list.get(i);
        }
        currentSize += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't get element at index " + index);
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > currentSize - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't set element at index " + index);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > currentSize - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element at index " + index);
        }
        T[] updatedElements = (T[]) new Object[currentCapacity];
        for (int i = 0, j = 0; i < currentSize; i++) {
            if (i != index) {
                updatedElements[j] = elements[i];
                j++;
            }
        }
        T element = elements[index];
        elements = updatedElements;
        currentSize--;

        return element;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);

        if (index >= 0) {
            return remove(index);
        }

        throw new NoSuchElementException("Can't remove " + element + " from the list");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void increaseCapacity() {
        int newCapacity = currentCapacity + currentCapacity / 2;
        currentCapacity = newCapacity;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private int findIndex(T value) {
        for (int i = 0; i < currentSize; i++) {
            if (compareObjects(elements[i], value)) {
                return i;
            }
        }

        return -1;
    }

    private boolean compareObjects(T obj1, T obj2) {
        if (obj1 == null || obj2 == null) {
            return obj1 == null && obj2 == null;
        }

        return obj1.equals(obj2);
    }
}
