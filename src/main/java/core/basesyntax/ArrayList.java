package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY_ELEMENT_DATA = 10;
    public static final double CAPACITY_GROW_FACTOR = 1.5;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) (new Object[DEFAULT_CAPACITY_ELEMENT_DATA]);
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = grow(elementData);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckForAddMethod(index);
        if (size >= elementData.length) {
            elementData = grow(elementData);
        }
        int numberElementToCopy = size - index;
        if (numberElementToCopy > 0) {
            System.arraycopy(elementData, index, elementData, index + 1, numberElementToCopy);
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
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        final T removedElement = elementData[index];
        int numberElementToCopy = size - index - 1;
        if (numberElementToCopy > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numberElementToCopy);
        }
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null) {
                if (element == null) {
                    return remove(i);
                }
            } else if (elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list is exist: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void indexCheckForAddMethod(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private T[] grow(T[] elementData) {
        int newCapacity = (int) (elementData.length * CAPACITY_GROW_FACTOR);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData,
                0, newArray,
                0, elementData.length);
        return newArray;
    }

}
