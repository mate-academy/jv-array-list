package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements; // Internal array to store elements
    private int size; // Number of elements in the list

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        // Create the internal array with the initial capacity
        elements = (T[]) new Object[initialCapacity];
        size = 0; // Initially, the list is empty
    }

    /**
     * Adds an element to the end of the list.
     */
    @Override
    public void add(T value) {
        ensureCapacity(size + 1); // Ensure enough capacity for the new element
        elements[size++] = value; // Add the element and increment the size
    }

    /**
     * Adds an element at the specified index in the list.
     *
     * @throws ArrayListIndexOutOfBoundsException if the index is invalid
     */
    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index); // Check if the index is within the valid range
        ensureCapacity(size + 1); // Ensure enough capacity for the new element

        // Shift elements to the right from the specified index to create space for the new element
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value; // Add the element at the specified index
        size++; // Increment the size
    }

    /**
     * Adds all elements from the given list to the end of the current list.
     */
    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();

        // Ensure enough capacity to add all elements
        ensureCapacity(size + listSize);

        // Add each element of the given list to the end of the current list using an indexed loop
        for (int i = 0; i < listSize; i++) {
            elements[size++] = list.get(i);
        }
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @throws ArrayListIndexOutOfBoundsException if the index is invalid
     */
    @Override
    public T get(int index) {
        checkIndex(index); // Check if the index is within the valid range
        return elements[index]; // Return the element at the specified index
    }

    /**
     * Replaces the element at the specified index in the list with the given value.
     *
     * @throws ArrayListIndexOutOfBoundsException if the index is invalid
     */
    @Override
    public void set(T value, int index) {
        checkIndex(index); // Check if the index is within the valid range
        elements[index] = value; // Update the element at the specified index with the new value
    }

    /**
     * Removes and returns the element at the specified index in the list.
     *
     * @throws ArrayListIndexOutOfBoundsException if the index is invalid
     */
    @Override
    public T remove(int index) {
        checkIndex(index); // Check if the index is within the valid range
        T removedElement = elements[index]; // Get the element to be removed

        // Shift elements to the left from the index to overwrite the element being removed
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // Set the last element to null and decrement the size

        return removedElement; // Return the removed element
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @throws NoSuchElementException if the element is not found
     */
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                // If the element is found, remove it using the index and return the removed element
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
        // If the element is not found, throw a NoSuchElementException
    }

    /**
     * Returns the number of elements in the list.
     */
    @Override
    public int size() {
        return size; // Return the size of the list
    }

    /**
     * Checks if the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0; // Check if the size is zero
    }

    /**
     * Ensures that the internal array has enough capacity to accommodate
     * the specified number of elements.
     * If not, the internal array is resized to have a larger capacity.
     *
     * @param capacity the desired capacity
     */
    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            // Increase the capacity by 50%
            int newCapacity = elements.length + (elements.length >> 1);
            // Create a new array with the increased capacity
            T[] newElements = (T[]) new Object[newCapacity];
            // Copy the existing elements to the new array
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements; // Update the reference to the new array
        }
    }

    /**
     * Checks if the given index is within the valid range for adding elements.
     * If not, throws an exception.
     *
     * @param index the index to check
     * @throws ArrayListIndexOutOfBoundsException if the index is invalid
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                "Invalid index for adding elements: " + index);
        }
    }

    /**
     * Checks if the given index is within the valid range for accessing elements.
     * If not, throws an exception.
     *
     * @param index the index to check
     * @throws ArrayListIndexOutOfBoundsException if the index is invalid
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
