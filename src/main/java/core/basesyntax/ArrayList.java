package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VALUE = 10;
    private static final double MORE_SIZE = 1.5;

    private Object[] array;

    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_VALUE];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            int extension = (int) (array.length * MORE_SIZE);
            Object[] newArray = new Object[extension];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        ensureCapacity();
        size++;
        ensureCapacity();

        for (int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity();
        for (int i = 0; i < list.size(); i++) {
            array[size++] = list.get(i);
            ensureCapacity();
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object[] tempArray = new Object[size - index - 1];
        final T removedElement = (T) array[index];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i] = array[index + 1 + i];
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        for (int i = 0; i < tempArray.length; i++) {
            array[index + i] = tempArray[i];
        }
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found for removal: " + element);
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
