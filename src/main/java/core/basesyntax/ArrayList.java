package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayOfObjects;
    private int size;

    public ArrayList() {
        arrayOfObjects = new Object[DEFAULT_CAPACITY];
    }

    public void grow() {
        Object[] temporaryArray = new Object[(int)(size * 1.5)];
        for (int i = 0; i < size; i++) {
            temporaryArray[i] = arrayOfObjects[i];
        }
        arrayOfObjects = temporaryArray;
    }

    public int doesTheElementExist(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayOfObjects[i] || element != null
                    && element.equals(arrayOfObjects[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Element dosen`t exist");
    }

    @Override
    public void add(T value) {
        if (size == arrayOfObjects.length) {
            grow();
        }
        arrayOfObjects[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cant add element to this index");
        }
        size++;
        Object[] temporaryArray = new Object[size];
        for (int i = 0; i < index; i++) {
            temporaryArray[i] = arrayOfObjects[i];
        }
        temporaryArray[index] = value;
        for (int i = index + 1; i < size; i++) {
            temporaryArray[i] = arrayOfObjects[i - 1];
        }
        arrayOfObjects = temporaryArray;
    }

    @Override
    public void addAll(List<T> list) {
        if (arrayOfObjects.length < list.size() + size) {
            while (arrayOfObjects.length >= list.size() + size) {
                grow();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            arrayOfObjects[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        return (T) arrayOfObjects[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        arrayOfObjects[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        Object[] temporaryArray = new Object[size];
        for (int i = 0; i < index; i++) {
            temporaryArray[i] = arrayOfObjects[i];
        }
        for (int i = index + 1; i < size; i++) {
            temporaryArray[i - 1] = arrayOfObjects[i];
        }
        Object temporaryObject = arrayOfObjects[index];
        arrayOfObjects = temporaryArray;
        size--;
        return (T) temporaryObject;
    }

    @Override
    public T remove(T element) {
        int index = doesTheElementExist(element);
        Object[] temporaryArray = new Object[size];
        for (int i = 0; i < index; i++) {
            temporaryArray[i] = arrayOfObjects[i];
        }
        for (int i = index + 1; i < size; i++) {
            temporaryArray[i - 1] = arrayOfObjects[i];
        }
        Object temporaryObject = arrayOfObjects[index];
        arrayOfObjects = temporaryArray;
        size--;
        return (T) temporaryObject;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 1; i < arrayOfObjects.length; i++) {
            if (arrayOfObjects[i] != null) {
                return false;
            }
        }
        return true;
    }
}
