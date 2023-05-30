package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ADD_CAPACITY = 1.5;
    private T[] elementsArray;
    private int size = 0;

    public ArrayList() {
        elementsArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementsArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        grow();
        System.arraycopy(elementsArray, index, elementsArray, index + 1, size - index);
        elementsArray[index] = value;
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
        checkIndex(index);
        return elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elementsArray[index];
        size--;
        System.arraycopy(elementsArray, index + 1, elementsArray, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementsArray[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found.");
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
        if (size == elementsArray.length) {
            int newCapacity = (int) (size * ADD_CAPACITY);
            T[] newValues = (T[]) new Object[newCapacity];
            System.arraycopy(elementsArray, 0, newValues, 0, elementsArray.length);
            elementsArray = newValues;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid: " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid: " + index);
        }
    }
}
