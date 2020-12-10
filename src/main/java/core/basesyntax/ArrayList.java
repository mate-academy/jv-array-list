package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_RESIZE = 1.5;

    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public ArrayList(int initialCapacity) {
        elementData = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (elementData.length == size && size == index) {
            grow();
            elementData[index] = value;
            size++;
        } else if ((index < size) && (index > 0)) {
            grow();
            size++;
            System.arraycopy(elementData, index, elementData, index + 1,
                    size - index);
            elementData[index] = value;
        } else {
            elementData[index] = value;
            size++;
        }
        isValid(index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isValid(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        isValid(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isValid(index);
        T remove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return remove;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null || elementData[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Don't have " + t + " in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isValid(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index
                    + " is out of the range of list size " + size);
        }
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, (int) (elementData.length * DEFAULT_RESIZE));
    }
}
