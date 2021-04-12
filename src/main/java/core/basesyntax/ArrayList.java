package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int arraySize;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T[] grow() {
        return (T[]) new Object[elementData.length + elementData.length / 2];
    }

    @Override
    public void add(T value) {
        if (elementData.length > arraySize + 1) {
            elementData[arraySize++] = value;
        } else {
            T[] newElementData = grow();
            System.arraycopy(elementData, 0, newElementData,
                            0, arraySize);
            newElementData[arraySize++] = value;
            elementData = newElementData;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (index < elementData.length) {
            System.arraycopy(elementData, index, elementData,
                            index + 1, arraySize - index);
            elementData[index] = value;
            arraySize++;
        } else if (index >= elementData.length) {
            T[] newElementData = grow();
            System.arraycopy(elementData, 0, newElementData,
                    0, index - 1);
            newElementData[index] = value;
            System.arraycopy(elementData, index, newElementData,
                    index + 1, arraySize - index);
            elementData[arraySize++] = (T) newElementData;
        } else {
            elementData[arraySize++] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        T[] newElementData = (T[]) new Object[list.size()];
        for (int i = 0; i < newElementData.length; i++) {
            newElementData[i] = list.get(i);
        }
        if (newElementData.length + arraySize > elementData.length) {
            newElementData = grow();
            System.arraycopy(elementData, 0, newElementData,
                            0, arraySize);
            System.arraycopy(elementData, 0, newElementData,
                            0, newElementData.length);
            elementData = newElementData;
        } else {
            System.arraycopy(newElementData, 0, elementData,
                             arraySize, newElementData.length);
        }
        arraySize += newElementData.length;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > arraySize - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        T elementToRemove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                        arraySize - index - 1);
        arraySize--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        boolean isElementExist = false;
        T elementToRemove = null;
        for (int i = 0; i < arraySize; i++) {
            if (elementData[i] == null && element == null) {
                elementToRemove = remove(i);
                return elementToRemove;
            } else if (elementData[i] != null && element.equals(elementData[i])) {
                elementToRemove = remove(i);
                isElementExist = true;
            }
        }
        if (isElementExist == false) {
            throw new NoSuchElementException("Element is not found");
        }
        return elementToRemove;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }
}
