package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_VOLUME = 10;
    private static final String INDEX_ERROR_MESSAGE = "Can not to find such element";
    private static final String INDEX_OUT_OF_THE_BROAD = "Index out of the broad";
    private Object [] mainArray;
    private int size;
    private Object removedObject;

    public ArrayList() {
        mainArray = new Object[DEFAULT_VOLUME];
    }

    @Override
    public void add(T value) {
        if (mainArray.length == size) {
            grow();
        }
        mainArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkFoExceptionIndex(index);
        if (mainArray.length == size) {
            grow();
        }
        Object [] bufferArray = new Object[mainArray.length + 1];
        System.arraycopy(mainArray, 0, bufferArray, 0, index);
        bufferArray[index] = value;
        System.arraycopy(mainArray, index, bufferArray, index + 1, mainArray.length - index);
        mainArray = bufferArray;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkFoExceptionIndex(index);
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_THE_BROAD);
        }
        return (T) mainArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkFoExceptionIndex(index);
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_THE_BROAD);
        }
        mainArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkFoExceptionIndex(index);
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_THE_BROAD);
        }
        removedObject = mainArray[index];
        removeIndex(index);
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == mainArray[i] || element != null && element.equals(mainArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can not to find such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public void grow() {
        Object[] bufferArray = new Object[mainArray.length + mainArray.length / 2];
        for (int i = 0; i < mainArray.length; i++) {
            bufferArray[i] = mainArray[i];
        }
        mainArray = bufferArray;
    }

    public void removeIndex(int index) {
        mainArray[index] = null;
        if (index != size) {
            System.arraycopy(mainArray, index + 1, mainArray, index, size - index - 1);
        }
        size--;
    }

    public void checkFoExceptionIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
    }
}
