package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final int firstArrayIndex = 0;
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resize(firstArrayIndex);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkForIndex(index);
        if (size == array.length) {
            resize(index);
        }
        if (array[index] != null) {
            Object[] oldArray = array;
            System.arraycopy(oldArray, index, array, index + 1, size - index);
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
        checkForIndexRemoveGetSet(index);
        return (T) array[index];
    }

    public void set(T value, int index) {
        checkForIndexRemoveGetSet(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForIndexRemoveGetSet(index);
        Object removedObject = array[index];
        removeIndex(index);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
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

    public void resize(int index) {
        Object[] oldArray = array;
        array = new Object[oldArray.length + (oldArray.length / 2)];
        System.arraycopy(oldArray, index, array, index, size);
    }

    public void removeIndex(int index) {
        checkForIndexRemoveGetSet(index);
        Object[] oldArray = array;
        int lengthNumber = 1;
        if (size == 1) {
            lengthNumber--;
        }
        System.arraycopy(oldArray, index + 1, array, index, size - index - lengthNumber);
    }

    public void checkForIndex(int index) {
        if (index - 1 > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
    }

    public void checkForIndexRemoveGetSet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
    }
}
