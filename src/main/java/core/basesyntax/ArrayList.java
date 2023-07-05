package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private Object[] innerArray;
    private int size = 0;

    public ArrayList() {
        innerArray = new Object[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        innerArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        growIfArrayFull();
        if (size == 0) {
            add(value);
        } else if (size == 1) {
            innerArray[1] = innerArray[0];
            innerArray[0] = value;
            size++;
        } else {
            for (int i = size; i > index; i--) {
                innerArray[i] = innerArray[i - 1];
            }
            innerArray[index] = value;
            size++;
        }
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
        return (T) innerArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        innerArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedItem = (T) innerArray[index];
        for (int i = index; i < size - 1; i++) {
            innerArray[i] = innerArray[i + 1];
        }
        size--;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        T removedItem = null;
        for (int i = 0; i < size; i++) {
            T castedObject = (T) innerArray[i];
            if ((castedObject == element) || (castedObject != null
                    && castedObject.equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size + 1 >= innerArray.length) {
            Object[] newArray = new Object[innerArray.length + (innerArray.length >> 1)];
            System.arraycopy(innerArray, 0, newArray, 0, innerArray.length);
            innerArray = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size && index != 0) {
            throw new ArrayListIndexOutOfBoundsException("There is no element at index " + index);
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can't input a negative index. "
                    + index + " is below zero!");
        }
    }
}
