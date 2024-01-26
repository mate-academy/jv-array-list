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
        resizeIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is"
                    + " larger than the array size");
        }
        resizeIfNeeded();

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        resizeIfNeeded();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexException(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexException(index);
        resizeIfNeeded();
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element with such data was not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            T[] clonableObject = (T[]) new Object[newCapacity];

            for (int i = 0; i < elementData.length; i++) {
                clonableObject[i] = elementData[i];
            }
            elementData = clonableObject;
        }
    }

    private void checkIndexException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The specified index is"
                    + " larger than the array size");
        }
    }
}
