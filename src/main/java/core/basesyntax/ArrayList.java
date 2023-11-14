package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private final T[] emptyElementData;
    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
        this.emptyElementData = (T[]) new Object[]{};
    }

    public T[] grow() {
        int newCapacity = 0;
        int oldCapacity = elementData.length;

        if (oldCapacity > 0 || elementData != emptyElementData) {
            newCapacity = oldCapacity + (oldCapacity >> 1);
        }

        T[] oldArray = elementData;
        elementData = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            elementData[i] = oldArray[i];
        }
        return elementData;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i <= size; i++) {
            elementData[size] = value;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T elements;
        for (int i = 0; i < list.size(); i++) {
            if (size == elementData.length) {
                elementData = grow();
            }
            elements = list.get(i);
            elementData[size] = elements;
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out Of Bounds Exception");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of Bound Exception");
        }
        for (int i = 0; i < size; i++) {
            elementData[i] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index Of Bound Exception");
        }
        System.arraycopy(elementData, 0, elementData, 0, index);
        T element = elementData[index];
        System.arraycopy(elementData,index + 1,elementData,index,elementData.length - (index + 1));
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int indexRemovedElement;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || (element != null && element.equals(elementData[i]))) {
                indexRemovedElement = i;
                System.arraycopy(elementData, 0, elementData, 0, indexRemovedElement);
                System.arraycopy(elementData,indexRemovedElement + 1,
                        elementData,indexRemovedElement,
                        elementData.length - (indexRemovedElement + 1));
                size--;
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
