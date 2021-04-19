package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayOfObjects;
    private int size;

    public ArrayList() {
        arrayOfObjects = new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        Object[] temporaryArray = new Object[(int)(size * 1.5)];
        System.arraycopy(arrayOfObjects,0,temporaryArray,0,arrayOfObjects.length);
        arrayOfObjects = temporaryArray;
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
        System.arraycopy(arrayOfObjects,index,arrayOfObjects,index + 1,size - 1);
        arrayOfObjects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (arrayOfObjects.length < list.size() + size) {
            while (arrayOfObjects.length >= list.size() + size) {
                grow();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkSize(index);
        return (T) arrayOfObjects[index];
    }

    private void checkSize(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
    }

    @Override
    public void set(T value, int index) {
        checkSize(index);
        arrayOfObjects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkSize(index);
        Object temporaryObject = arrayOfObjects[index];
        System.arraycopy(arrayOfObjects,index + 1,arrayOfObjects,index,size - 1);
        size--;
        return (T) temporaryObject;
    }

    @Override
    public T remove(T element) {
        int index = doesTheElementExist(element);
        Object temporaryObject = arrayOfObjects[index];
        remove(index);
        return (T) temporaryObject;
    }

    private int doesTheElementExist(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayOfObjects[i] || element != null
                    && element.equals(arrayOfObjects[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Element dosen`t exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
