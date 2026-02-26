package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        this.ensureCapacity();
        this.elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        this.checkIndexForAdd(index);
        this.ensureCapacity();
        System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
        this.elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        this.checkIndex(index);
        return (T) this.elementData[index];
    }

    @Override
    public void set(T value, int index) {
        this.checkIndex(index);
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        return this.fastRemove(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return this.remove(i);
            }
        }

        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void ensureCapacity() {
        if (this.elementData != null && this.size == this.elementData.length) {
            Object[] newArray = new Object[(int) (this.size() + (this.elementData.length >> 1))];
            System.arraycopy(this.elementData, 0, newArray, 0, this.size);
            this.elementData = newArray;
        }
    }

    private T fastRemove(int index) {
        this.checkIndex(index);
        Object removedElement = this.elementData[index];
        int elementsToMove = size - index - 1;
        if (elementsToMove > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, elementsToMove);
        }
        this.elementData[--size] = null;
        return (T) removedElement;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
