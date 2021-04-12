package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] arrayOfObjects;
    private int size;

    public ArrayList() {
        arrayOfObjects = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize();
        arrayOfObjects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        checkSize();
        System.arraycopy(arrayOfObjects, index, arrayOfObjects, index + 1, size - index);
        arrayOfObjects[index] = value;
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
        checkIndex(index);
        return (T) arrayOfObjects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayOfObjects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object objectByIndex = arrayOfObjects[index];
        System.arraycopy(arrayOfObjects, index + 1, arrayOfObjects, index, size - index);
        size--;
        return (T) objectByIndex;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayOfObjects.length; i++) {
            if (arrayOfObjects[i] == element
                    || element != null && element.equals(arrayOfObjects[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacity() {
        Object[] tempArray = new Object[size + size / 2];
        System.arraycopy(arrayOfObjects, 0, tempArray, 0, arrayOfObjects.length);
        arrayOfObjects = tempArray;
    }

    private void checkIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException("This index doesn't exist");
        }
    }

    private void checkSize() {
        if (size == arrayOfObjects.length) {
            growCapacity();
        }
    }
}
