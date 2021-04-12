package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;

    private T[] elementData = (T[]) new Object[DEFAULT_SIZE];
    private int size;

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(elementData.length);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        if (DEFAULT_SIZE > elementData.length || size < elementData.length) {
            System.arraycopy(elementData, index, elementData,
                    index + 1, size - index);
            elementData[index] = value;
        } else {
            T[] widerList = grow(elementData.length);
            System.arraycopy(elementData, index, widerList, index + 1, size - index);
            widerList[index] = value;
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index passed to the method is invalid");
        }
        return droppedValue(elementData, index);
    }

    @Override
    public T remove(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element.equals(elementData[i])) {
                index = i;
                break;
            } else if (size - 1 == i && index == 0) {
                throw new NoSuchElementException("There is no such element present");
            }
        }
        return droppedValue(elementData, index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(int oldSize) {
        T[] newElementData = (T[]) new Object[(int) (oldSize * INCREASING_COEFFICIENT)];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        return newElementData;
    }

    private T droppedValue(T[] elementData, int index) {
        final T oldValue = elementData[index];
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[size - 1] = null;
        size -= 1;
        return oldValue;
    }
}
