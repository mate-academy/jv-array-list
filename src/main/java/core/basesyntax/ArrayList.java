package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    // Constants for setting the size and increasing the size of the list by default.
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_MULTIPLIER = 1.5;
    // Class fields
    private int capacity; // current list capacity
    private T[] elementData; // array of list elements
    private int size; // current list size

    // Class constructor that sets the default list capacity.
    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elementData = (T[]) new Object[capacity];
    }

    // Method for increasing the capacity of the list when full.
    private void resizeIfNeeded() {
        if (size == capacity) {
            capacity *= DEFAULT_MULTIPLIER;
            T[] expandedArray = (T[]) new Object[capacity];
            System.arraycopy(elementData, 0, expandedArray, 0, size);
            elementData = expandedArray;
        }
    }

    // Method to check if two objects are equal.
    private boolean isEquals(Object first, Object second) {
        return first == second || first != null && first.equals(second);
    }

    // Method for checking if the index is within the allowed bounds of the list.
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of ArrayList bound!");
        }
    }

    @Override
    public boolean isEmpty() {
        // Return true if the array is empty (size == 0), false otherwise
        return size == 0;
    }

    @Override
    public int size() {
        // Return the current size of the array
        return size;
    }

    // Adding an element to the end of the list.
    @Override
    public void add(T value) {
        resizeIfNeeded();
        elementData[size++] = value;
    }

    // Adding an element to the specified position in the list.
    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        resizeIfNeeded();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    // Adding all elements from the passed list to the current list.
    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    // Get the list element by index.
    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // Replacing the list element by index with the passed element.
    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    // Removes the first occurrence of the specified element from this list.
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such an element does not exist!");
    }

    // Removing the list element by index and returning the removed element.
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedValue;
    }
}
