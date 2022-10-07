package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int firstArrayIndex = 0;
    private int size;
    private Object[] oldArray;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
        oldArray = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            reSize();
        }
        if (value == null) {
            size++;
            return;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForIndex(index);
        if (size == array.length) {
            reSize(index);
        }
        if (array[index] != null) {
            oldArray = array;
            System.arraycopy(oldArray, index, array, index + 1, size - index);
        }
        if (value == null) {
            reSize(index);
            size++;
            return;
        }
        array[index] = value;
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
        checkForIndexGetAndSet(index);
        return (T) array[index];
    }

    public void set(T value, int index) {
        checkForIndexGetAndSet(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForIndexRemove(index);
        Object removedObject = array[index];
        removeIndex(index);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == null && element == null)
                    || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return array[firstArrayIndex] == null;
    }

    public void reSize() {
        oldArray = array;
        array = new Object[oldArray.length + (oldArray.length / 2)];
        System.arraycopy(oldArray, firstArrayIndex, array, firstArrayIndex, size);
    }

    public void reSize(int index) {
        oldArray = array;
        array = new Object[oldArray.length + (oldArray.length / 2)];
        System.arraycopy(oldArray, index, array, index + 1, size);
    }

    public void removeIndex(int index) {
        checkForIndexRemove(index);
        oldArray = array;
        if (index == 0) {
            System.arraycopy(oldArray, index + 1, array, index, size);
            return;
        }
        if (size == array.length) {
            index = 0;
        }
        System.arraycopy(oldArray, index + 1, array, index, size - 1);
    }

    public void checkForIndex(int index) {
        if (index - 1 > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
    }

    public void checkForIndexRemove(int index) {
        if (index + 1 > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
    }

    public void checkForIndexGetAndSet(int index) {
        if (index == size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
    }
}
