package core.basesyntax;

import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] internalData;
    private int size;

    public ArrayList() {
        internalData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity > 0) {
            internalData = (T[]) new Object[capacity];
        } else if (capacity == 0) {
            internalData = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new RuntimeException("Can't create ArrayList with capacity " + capacity);
        }
    }

    public ArrayList(Collection<? extends T> collection) {
        if (collection == null) {
            throw new RuntimeException("Can't create ArrayList from collection " + collection);
        }
        T[] inputCollection = (T[]) collection.toArray();
        if (inputCollection.length == 0) {
            internalData = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            internalData = inputCollection;
            size = inputCollection.length;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        internalData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index value is incorrect " + index);
        }
        ensureCapacity();
        System.arraycopy(internalData, index,
                internalData, index + 1, size - index);
        internalData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("Can't add elements from collection " + list);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return internalData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        internalData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removedElement = internalData[index];
        System.arraycopy(internalData, index + 1,
                internalData, index, size - index - 1);
        internalData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == internalData[i]
                    || (element != null && element.equals(internalData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There are no such element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growElementData() {
        int newCapacity = internalData.length + (internalData.length >> 1);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(internalData, 0, newElementData, 0, internalData.length);
        internalData = newElementData;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index value is incorrect " + index);
        }
    }

    private void ensureCapacity() {
        if (size == internalData.length) {
            growElementData();
        }
    }
}
