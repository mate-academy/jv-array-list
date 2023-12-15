package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final String ERROR_INDEX_MESSAGE = "Incorrect index ";
    private static final String ERROR_ELEMENT_MESSAGE = "Element doesn't exist";
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_SIZE];
    }

    public void boostIfSizeBigger(int minSize) {
        if (minSize > objects.length) {
            int newCapacity = objects.length + (objects.length >> 1);
            if (newCapacity < minSize) {
                newCapacity = minSize;
            }
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(objects, 0, newArray, 0, objects.length);
            objects = newArray;
        }
    }

    private void checkIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_INDEX_MESSAGE + index);
        }
    }

    @Override
    public void add(T value) {
        boostIfSizeBigger(size + 1);
        objects[size++] = value;

    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_INDEX_MESSAGE + index);
        }
        boostIfSizeBigger(size + 1);
        System.arraycopy(objects, index, objects, index + 1, size - index);
        objects[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        boostIfSizeBigger(size + list.size());
        for (int i = 0; i < list.size(); i++) {
            objects[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        boostIfSizeBigger(index);
        T returnValue = (T) objects[index];
        if (index == size - 1) {
            objects[--size] = null;
            return returnValue;
        }
        System.arraycopy(objects, index + 1, objects, index, size - index - 1);
        objects[--size] = null;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T currentValue = get(i);
            if (currentValue == element || currentValue != null && currentValue.equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ERROR_ELEMENT_MESSAGE);
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
