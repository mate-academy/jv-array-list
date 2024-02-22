package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_SIZE = 10;
    private static final int ADDITIONAL_SIZE = 5;
    private static final int NOT_FOUND = -1;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        if (!isFullArray()) {
            array[size] = value;
            size++;
        } else {
            resizeArray();
            add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        } else if (!isFullArray()) {
            insertElementAtIndex(value, index);
        } else {
            resizeArray();
            add(value, index);
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
        if (isInvalidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (isInvalidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isInvalidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        } else {
            T removedElement = array[index];
            removeByIndex(index);
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        if (isInvalidIndex(index)) {
            throw new NoSuchElementException("");
        } else {
            T removedElement = array[index];
            removedElement = array[index];
            removeByIndex(index);
            return removedElement;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFullArray() {
        return size == array.length;
    }

    private void resizeArray() {
        int newSize = size + ADDITIONAL_SIZE;
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void insertElementAtIndex(T value, int index) {
        T[] newArray = (T[]) new Object[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        array = newArray;
        size++;
    }

    private boolean isInvalidIndex(int index) {
        return index >= size || index < 0;
    }

    private int findIndex(T element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(element, array[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void removeByIndex(int index) {
        T[] newArray = (T[]) new Object[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        array = newArray;
        size--;
    }
}
