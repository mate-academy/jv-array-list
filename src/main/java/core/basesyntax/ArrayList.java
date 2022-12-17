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
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(list.size());
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
        removeByIndex(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == null) {
                index = i;
            }
            if (element != null && element.equals(elementData[i])) {
                index = i;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Can't find the element");
        }

        T oldValue = get(index);
        removeByIndex(index);
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    private void ensureCapacity() {
        if (size + 1 > elementData.length) {
            grow();
        }
    }

    private void ensureCapacity(int newElementsToAdd) {

        if (size + newElementsToAdd >= elementData.length) {
            grow(size + newElementsToAdd);
        }
    }

    private void grow(int minNewCapacity) {
        int currentCapacity = elementData.length;
        int newCapacity;
        int bufferCellsRemain;
        do {
            newCapacity = currentCapacity + (currentCapacity >> 1);
            bufferCellsRemain = newCapacity - minNewCapacity;
            currentCapacity = newCapacity;
        } while (bufferCellsRemain < 0);

        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        this.elementData = newElementData;
    }

    private void grow() {
        grow(size + 1);
    }

    private void removeByIndex(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
    }
}
