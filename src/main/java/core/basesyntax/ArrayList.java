package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] objectArray;
    private int sizeArray = 0;

    public ArrayList() {
        objectArray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (sizeArray == objectArray.length) {
            newCapacity();
        }
        objectArray[sizeArray] = value;
        sizeArray++;
    }

    @Override
    public void add(T value, int index) {
        if (index == sizeArray) {
            add(value);
            return;
        }
        checkIndex(index);
        if (sizeArray == objectArray.length) {
            newCapacity();
        }
        System.arraycopy(objectArray, index, objectArray, index + 1, sizeArray - index);
        objectArray[index] = value;
        sizeArray++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) objectArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objectArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deleteElement = (T) objectArray[index];
        System.arraycopy(objectArray, index + 1, objectArray, index, sizeArray - index - 1);
        objectArray[--sizeArray] = null;
        return deleteElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeArray; i++) {
            if ((objectArray[i] != null && objectArray[i].equals(element))
                    || objectArray[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return sizeArray;
    }

    @Override
    public boolean isEmpty() {
        return sizeArray == 0;
    }

    public void newCapacity() {
        T[] newArray = (T[]) new Object[objectArray.length + (objectArray.length >> 1)];
        System.arraycopy(objectArray, 0, newArray, 0, objectArray.length);
        objectArray = newArray;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= sizeArray) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong");
        }
    }
}
