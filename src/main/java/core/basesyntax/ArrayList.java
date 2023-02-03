package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentCapacity = 10;
    private int size = 0;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        grow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        rangeCheckForGet(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGet(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGet(index);
        T oldValue = elementData[index];
        removeOneElement(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] != null) {
                    remove(i);
                    return element;
                }
            } else if (elementData[i] != null && elementData[i].equals(element)) {
                removeOneElement(i);
                return element;
            }
        }
        throw new NoSuchElementException("Elements do not exist " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == currentCapacity - 1) {
            currentCapacity += currentCapacity / 2;
            T[] newElementData = (T[]) new Object[currentCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void rangeCheckForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void removeOneElement(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
    }

}
