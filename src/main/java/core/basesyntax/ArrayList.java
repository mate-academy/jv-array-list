package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final String WRONG_INDEX_MSG = "Invalid index. Check the list size";
    public static final String NO_SUCH_ELEMENT = "Can't find such element";
    public static final int INITIAL_SIZE = 10;

    private T[] array = (T[]) new Object[INITIAL_SIZE];
    private int currentSize = 0;
    private int maxSize = INITIAL_SIZE;

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
        if (index <= currentSize) {
            T[] buffer = Arrays.copyOfRange(array, index, currentSize);
            array[index] = value;
            System.arraycopy(buffer, 0, array, index + 1, buffer.length);
            currentSize++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            sizeCheck();
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
                System.arraycopy(array, i + 1, array, i, currentSize - i - 1);
                currentSize--;
                return element;
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
        array = Arrays.copyOf(array, maxSize);
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
