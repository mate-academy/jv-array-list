package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ABSENT_INDEX = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int SHIFT = 1;
    private static final int START = 0;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size < elementData.length) {
            elementData[size] = value;
        } else {
            insert(value, size, expand());
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index, size + 1);
        /*if (index == size) {
            add(value);
        }*/
        if (size < elementData.length) {
            insert(value, index, new Object[elementData.length]);
        } else {
            insert(value, index, expand());
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
        indexValidation(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index, size);
        T removed = (T) elementData[index];
        System.arraycopy(elementData, index + SHIFT,
                elementData, index, size - index - SHIFT);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = ABSENT_INDEX;
        for (int i = 0; i < size; i++) {
            T currElement = (T) elementData[i];
            if ((element == currElement) || (element != null && element.equals(currElement))) {
                index = i;
            }
        }
        if (index == ABSENT_INDEX) {
            throw new NoSuchElementException("There is no provided element in the list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == START;
    }

    private Object[] expand() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        return new Object[newCapacity];
    }

    private void insert(T value, int index, Object[] newElementData) {
        System.arraycopy(elementData, START, newElementData, START, index);
        newElementData[index] = value;
        System.arraycopy(elementData, index, newElementData, index + SHIFT, size - index);
        elementData = newElementData;
    }

    private void indexValidation(int index, int limit) {
        if (index < START || index >= limit) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index provided");
        }
    }
}
//List <Integer> i = ArrayList<>
