package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (checkIndex(index) && index == size) {
            add(value);
        } else {
            if (size == elementData.length) {
                grow();
            }
            T[] newElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            newElementData[index] = value;
            System.arraycopy(elementData, index, newElementData, index + 1, size);
            System.arraycopy(newElementData, 0, elementData, 0, newElementData.length);
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length - size < list.size()) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index + 1)) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index + 1)) {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (checkIndex(index)) {
            value = elementData[index];
            T[] newElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, newElementData, 0, index);
            System.arraycopy(elementData, index + 1, newElementData, index, size);
            System.arraycopy(newElementData, 0, elementData, 0, size);
            size--;
        }
        return value;
    }

    @Override
    public T remove(T element) {
        T value = null;
        int index = -1;
        if (null == element) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    index = i;
                    break;
                }
            }
            value = checkRemoveIndex(index);
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    index = i;
                    break;
                }
            }
            value = checkRemoveIndex(index);
        }
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index not valid");
        }
        return true;
    }

    private void grow() {
        int newCapacity = size;
        T[] newElementData;
        if (size == elementData.length) {
            newCapacity += newCapacity / 2;
            newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = (T[]) new Object[newCapacity];
            System.arraycopy(newElementData, 0, elementData, 0, size);
        }
    }

    private T checkRemoveIndex(int index) {
        if (index > -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Not such element to remove");
        }
    }
}
