package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    // default capacity for list
    private int size;
    private Object[] elementInfo; // element

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        elementInfo = new Object[initialCapacity];
    }

    private void grow() {
        if (size == elementInfo.length) {
            int oldCapacity = elementInfo.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementInfo, 0, newArray, 0, size);
            elementInfo = newArray;
        }
    }

    public void checkRangeAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index of element: " + index
                    + "Size of the list: " + size);
        }
        if (size == elementInfo.length) {
            grow();
        }
    }

    public void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index of element: " + index
                    + "Size of the list: " + size);
        }
    }

    @Override
    public void add(T value) {
        grow();
        elementInfo[size++] = value; // insert the new element
    }

    @Override
    public void add(T value, int index) {
        checkRangeAdd(index);
        System.arraycopy(elementInfo, index, elementInfo, // shift elements to the right
                index + 1, size - index);
        elementInfo[index] = value; // insert the new element
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
        checkRange(index);
        return (T) elementInfo[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elementInfo[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T oldPos = (T) elementInfo[index];
        int newPos = size - index - 1;
        if (newPos > 0) {
            System.arraycopy(elementInfo, index + 1, elementInfo, index, newPos);
        }
        elementInfo[--size] = null;
        return oldPos;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementInfo[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(elementInfo[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Array does not have such element: " + element);
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
