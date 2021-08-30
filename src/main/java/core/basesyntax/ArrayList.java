package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_CAPACITY = 1.5;
    private static final int ARRAYS_ZERO_BORDER = 0;
    private static final int INCREASE_INDEX_FOR_COPY = 1;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (elementData.length == size) {
            elementData = grow();
        }
        size++;
        if (index < size && index >= ARRAYS_ZERO_BORDER) {
            System.arraycopy(elementData, index, elementData,
                    index + INCREASE_INDEX_FOR_COPY,
                    size - (index + INCREASE_INDEX_FOR_COPY));
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is bigger than arrays size");
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length < size + list.size()) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }

    }

    @Override
    public T get(int index) {
        if (index < size && index >= ARRAYS_ZERO_BORDER) {
            return elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is bigger than arrays size");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= ARRAYS_ZERO_BORDER) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is bigger than arrays size");
        }

    }

    @Override
    public T remove(int index) {
        if (index >= size || index < ARRAYS_ZERO_BORDER) {
            throw new ArrayListIndexOutOfBoundsException("Index is bigger than arrays size");
        }
        T oldElement = elementData[index];
        if (size != elementData.length) {
            System.arraycopy(elementData, index + INCREASE_INDEX_FOR_COPY,
                    elementData, index, size - index);
            size--;
        } else {
            elementData[index] = null;
            size--;
        }
        return oldElement;
    }

    @Override
    public T remove(T element) {
        T oldElement = null;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element here");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * INCREASE_CAPACITY);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, ARRAYS_ZERO_BORDER,
                newElementData, ARRAYS_ZERO_BORDER, oldCapacity);
        return newElementData;
    }
}
