package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow(elementData, size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        addIndexValidator(index);
        if (size == elementData.length) {
            grow(elementData, size);
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
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
        indexValidator(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidator(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        indexValidator(index);
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidator(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid");
        }
    }

    private void addIndexValidator(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid");
        }
    }

    private void grow(T[] elementData, int size) {
        if ((size + 1) >= elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            this.elementData = newElementData;
        }
    }
}
