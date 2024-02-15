package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (!hasCapacity()) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        if (size + 1 > elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData,
                index + 1, elementData.length - index - 1);
        elementData[index] = value;
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
        if (hasIndexOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (hasIndexOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (hasIndexOutOfBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds Exception: " + index);
        }
        T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index, elementData.length - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int foundIndex = -1;
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || element != null && element.equals(elementData[i])) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1) {
            throw new NoSuchElementException("No such element in the List");
        }
        return remove(foundIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean hasCapacity() {
        return size < elementData.length;
    }

    private boolean hasIndexOutOfBounds(int index) {
        return index < 0 || index >= size;
    }

    private void grow() {
        int newSize = elementData.length + (elementData.length >> 1);
        T[] temp = (T[]) new Object[newSize];
        System.arraycopy(elementData, 0, temp, 0, elementData.length);
        this.elementData = temp;
    }
}
