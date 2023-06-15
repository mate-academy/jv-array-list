package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_SIZE];
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        if (index < size) {
            T[] tempElementData = (T[]) new Object[elementData.length];
            System.arraycopy(elementData, 0, tempElementData, 0, index + 1);
            tempElementData[index] = value;
            System.arraycopy(elementData, index, tempElementData, index + 1, size - index);
            elementData = tempElementData;
            size++;
            return;
        }
        if (index == size) {
            elementData[size] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new ArrayListIndexOutOfBoundsException("Can't add list");
        }
        while (elementData.length < size + list.size()) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (isInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (isInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isInvalid(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
        T[] tempElementData = (T[]) new Object[elementData.length];
        T deletedObject;
        if (index + 1 == elementData.length) {
            System.arraycopy(elementData,0,tempElementData,0,index);
            deletedObject = elementData[index];
            elementData = tempElementData;
            size--;
            return deletedObject;
        }
        System.arraycopy(elementData, 0, tempElementData, 0, index);
        System.arraycopy(elementData, index + 1, tempElementData, index, size - index);
        deletedObject = elementData[index];
        elementData = tempElementData;
        size--;
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element) || elementData[i]
                    != null && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Such element does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] tempElementData = (T[]) new Object[elementData.length + (elementData.length / 2)];
        System.arraycopy(elementData, 0, tempElementData, 0, size);
        elementData = tempElementData;
        return elementData;
    }

    private boolean isInvalid(int index) {
        return index >= size || index < 0;
    }
}
