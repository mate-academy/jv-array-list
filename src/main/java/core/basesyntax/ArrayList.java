package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_LENGTH];
        size = 0;
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
        rangeCheck(index);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listToAdd = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToAdd[i] = list.get(i);
        }
        System.arraycopy(listToAdd, 0, elementData, size, listToAdd.length);
        size += listToAdd.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T result = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index, size - index);
        size -= 1;
        return result;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (t.equals(elementData[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("There is no such element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity + (oldCapacity / 2);
            T[] bufferArray = (T[]) new Object[oldCapacity];
            System.arraycopy(elementData, 0, bufferArray,0, size);
            elementData = (T[]) new Object[newCapacity];
            System.arraycopy(bufferArray, 0, elementData,0, size);
        }
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is incorrect!");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is incorrect!");
        }
    }

}
