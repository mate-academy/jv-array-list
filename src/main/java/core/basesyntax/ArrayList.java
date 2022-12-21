package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_CAPACITY = 10;
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private T[] someData;
    private int size;

    public ArrayList() {
        someData = (T[]) new Object[DEFAULT_SIZE_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == someData.length) {
            arrayCopy();
        }
        someData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
        if (size == someData.length) {
            arrayCopy();
        }
        System.arraycopy(someData, index, someData, index + 1, size - index);
        someData[index] = value;
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
        return someData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        someData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = someData[index];
        System.arraycopy(someData, index + 1, someData, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (someData[i] == null && element == null
                    || someData[i] != null && someData[i].equals(element)) {
                size--;
                System.arraycopy(someData, i + 1, someData, i, size - i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element in this array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
        }
    }

    private void grow() {
        int newCapacity = (int) (someData.length * MAGNIFICATION_FACTOR);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(someData, 0,
                newArray, 0, size);
        someData = (T[]) newArray;
    }

    private void arrayCopy() {
        T[] someDataCopy = (T[]) new Object[(int) (someData.length * MAGNIFICATION_FACTOR)];
        System.arraycopy(someData,0,someDataCopy, 0, someData.length);
        someData = someDataCopy;
    }

}
