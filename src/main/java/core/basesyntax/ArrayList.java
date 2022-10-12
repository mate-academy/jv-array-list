package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final String WRONG_INDEX_MSG = "Invalid index. Check the list size";
    public static final String NO_SUCH_ELEMENT = "Can't find such element";
    public static final int INITIAL_SIZE = 10;

    private T[] array;
    private int currentSize;
    private int maxSize;

    public ArrayList() {
        array = (T[]) new Object[INITIAL_SIZE];
        maxSize = INITIAL_SIZE;
    }

    @Override
    public void add(T value) {
        sizeCheck();
        array[currentSize] = value;
        currentSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > currentSize) {
            throw new ArrayListIndexOutOfBoundsException(WRONG_INDEX_MSG);
        }
        sizeCheck();
        T[] buffer = (T[]) new Object[currentSize - index];
        System.arraycopy(array, index, buffer, 0, buffer.length);
        array[index] = value;
        System.arraycopy(buffer, 0, array, index + 1, buffer.length);
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T deletedValue = get(index);
        System.arraycopy(array, index + 1, array, index, currentSize - index - 1);
        currentSize--;
        return deletedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (element == array[i] || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT);
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void grow() {
        maxSize = (int) (array.length * 1.5);
        T[] newArray = (T[]) new Object[maxSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void sizeCheck() {
        if (currentSize == maxSize) {
            grow();
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= currentSize) {
            throw new ArrayListIndexOutOfBoundsException(WRONG_INDEX_MSG);
        }
    }
}
