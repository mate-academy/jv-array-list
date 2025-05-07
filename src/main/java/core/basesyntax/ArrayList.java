package core.basesyntax;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int capacity;
    private int currentSize;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        isIndexValidForAddingValues(index);
        if (index == currentSize) {
            add(value);
        } else {
            int partAfterIndexSize = currentSize - index;
            T[] partAfterIndex = (T[]) new Object[partAfterIndexSize];
            System.arraycopy(elements, index, partAfterIndex, 0, partAfterIndexSize);

            //Increase the size because of adding one element
            currentSize++;
            //Check if the modified capacity is valid
            growIfArrayFull();
            elements[index] = value;
            System.arraycopy(partAfterIndex, 0, elements, index + 1, partAfterIndexSize);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (T element : list) {
            add(element);
        }
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        T oldValue = elements[index];
        if (index == currentSize - 1) {
            currentSize--;
            T[] shortedArrayOfElements = (T[]) new Object[currentSize];

            System.arraycopy(elements, 0, shortedArrayOfElements, 0, currentSize);
            elements = shortedArrayOfElements;
        } else {
            int partAfterIndexSize = currentSize - index;
            T[] partAfterIndex = (T[]) new Object[partAfterIndexSize];

            System.arraycopy(elements, index + 1, partAfterIndex, 0, partAfterIndexSize);
            System.arraycopy(partAfterIndex, 0, elements, index, partAfterIndexSize);
            currentSize--;
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = getIndexOfValue(element);
        if (indexOfElement != -1) {
            return remove(indexOfElement);
        }
        throw new NoSuchElementException("Can't find the element - " + element);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position;
            @Override
            public boolean hasNext() {
                return position < currentSize;
            }

            @Override
            public T next() {
                T element = elements[position];
                position++;
                return element;
            }
        };
    }

    private void growIfArrayFull() {
        if (capacity <= currentSize) {
            int modifiedCapacity = capacity + (capacity >> 1);
            T[] newElementsStorage = (T[]) new Object[modifiedCapacity];

            System.arraycopy(elements, 0, newElementsStorage, 0, capacity);
            elements = newElementsStorage;
            capacity = modifiedCapacity;
            growIfArrayFull();
        }
    }

    private void isIndexValid(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index - " + index
                                                        + " is invalid to use!");
        }
    }

    private void isIndexValidForAddingValues(int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index - " + index
                                                        + " is invalid to add to the arraylist!");
        }
    }

    private int getIndexOfValue(T value) {
        for (int i = 0; i < currentSize; i++) {
            if ((value == elements[i]) || (value != null && value.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }
}
