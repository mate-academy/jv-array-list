package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int currentCapacity;
    private int realCapacity;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        realCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[currentCapacity] = value;
        currentCapacity++;
    }

    @Override
    public void add(T value, int index) {
        isIndexValidForAdding(index);
        if (index == currentCapacity) {
            add(value);
        } else {
            int partAfterIndexSize = currentCapacity - index;
            T[] partAfterIndex = (T[]) new Object[partAfterIndexSize];
            System.arraycopy(elements, index, partAfterIndex, 0, partAfterIndexSize);

            //Increase the size because of adding one element
            currentCapacity = currentCapacity + 1;
            //Check if the modified capacity is valid
            growIfArrayFull();
            elements[index] = value;
            System.arraycopy(partAfterIndex, 0, elements, index + 1, partAfterIndexSize);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexValidForUsing(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValidForUsing(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValidForUsing(index);
        T oldValue = elements[index];

        if (index == currentCapacity - 1) {
            int newCapacity = currentCapacity - 1;
            T[] shortedArrayOfElements = (T[]) new Object[newCapacity];

            System.arraycopy(elements, 0, shortedArrayOfElements, 0, newCapacity);
            elements = shortedArrayOfElements;
            currentCapacity--;
        } else {
            int partAfterIndexSize = currentCapacity - index;
            T[] partAfterIndex = (T[]) new Object[partAfterIndexSize];

            System.arraycopy(elements, index + 1, partAfterIndex, 0, partAfterIndexSize);
            System.arraycopy(partAfterIndex, 0, elements, index, partAfterIndexSize);
            currentCapacity--;
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = getIndexOfValue(element);
        if (indexOfElement != -1) {
            return remove(indexOfElement);
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return currentCapacity;
    }

    @Override
    public boolean isEmpty() {
        return currentCapacity == 0;
    }

    private void growIfArrayFull() {
        if (realCapacity <= currentCapacity) {
            int modifiedCapacity = realCapacity + (realCapacity >> 1);
            T[] newElementsStorage = (T[]) new Object[modifiedCapacity];

            System.arraycopy(elements, 0, newElementsStorage, 0, realCapacity);
            elements = newElementsStorage;
            realCapacity = modifiedCapacity;
            growIfArrayFull();
        }
    }

    private void isIndexValidForUsing(int index) {
        if (index >= currentCapacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index - " + index
                                                        + " is invalid to use!");
        }
    }

    private void isIndexValidForAdding(int index) {
        if (index > currentCapacity || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index - " + index
                                                        + " is invalid to add to the arraylist!");
        }
    }

    private int getIndexOfValue(T value) {
        for (int i = 0; i < currentCapacity; i++) {
            if ((value == elements[i]) || (value != null && value.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }
}
