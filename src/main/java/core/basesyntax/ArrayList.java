package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        extendCapacityIfListFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        extendCapacityIfListFull();
        System.arraycopy(elementData, index, elementData, index + 1, size++ - index);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        extendCapacityIfListFull();
        for (int i = 0; i < list.size(); i++) {
            T newElement = list.get(i);
            this.add(newElement);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = get(index);
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == null
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find the element \"" + element + "\"");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendCapacityIfListFull() {
        if (size >= elementData.length) {
            grow();
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is invalid");
        }
    }

    private void grow() {
        int currentCapacity = elementData.length;
        int newCapacity;
        newCapacity = currentCapacity + (currentCapacity >> 1);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        this.elementData = newElementData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is invalid");
        }
    }

    private void fastRemove(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size-- - index - 1);
    }
}
