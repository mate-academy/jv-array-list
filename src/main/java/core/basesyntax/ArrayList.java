package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!isIndexValid(index) && index != size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        if (index == size) {
            add(value);
        } else {
            addElementInMiddle(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexValid(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T removedElement = get(index);
        for (int i = 0; i + index + 1 < size; i++) {
            elementData[index + i] = elementData [index + i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        if (!isIndexValid(getIndex(t))) {
            return t;
        }
        remove(getIndex(t));
        return t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int newCapacity(int oldCapacity) {
        return (int) (oldCapacity * 1.5);
    }

    private T[] grow() {
        return Arrays.copyOf(elementData, newCapacity(size));
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }

    private int getIndex(T value) {
        int index = 0;
        for(T element: elementData) {
            if (value == element || value != null && value.equals(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private void addElementInMiddle(T value, int index) {
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }
}
