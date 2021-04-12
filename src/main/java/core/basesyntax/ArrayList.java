package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        array = new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (currentSize >= array.length) {
            array = grow(array);
        }
        array[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list bounds");
        }
        if (array.length == currentSize) {
            array = grow(array);
        }
        System.arraycopy(array, index, array, index + 1, currentSize - index);
        array[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Object[] listArray = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        for (int i = 0; i < listArray.length; i++) {
            add((T)list.get(i));
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
        final T value = (T) array[index];
        System.arraycopy(array, index + 1, array, index, currentSize - index);
        array[currentSize] = null;
        currentSize--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return this.remove(i);
            }
        }
        throw new NoSuchElementException("No such element here");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private Object[] grow(Object[] oldArray) {
        Object[] grownArray = new Object [oldArray.length + (oldArray.length >> 1)];
        System.arraycopy(oldArray, 0, grownArray, 0, currentSize);
        return grownArray;
    }

    private void checkIndex(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list bounds");
        }
    }
}
