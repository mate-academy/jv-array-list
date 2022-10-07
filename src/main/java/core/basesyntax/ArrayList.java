package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
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
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > currentSize) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on this position");
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
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't find element with this index");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > currentSize - 1) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= currentSize) {
            throw new ArrayListIndexOutOfBoundsException("Can't delete element "
                    + "on this position. Incorrect position");
        }
        T deletedValue = get(index);
        System.arraycopy(array, index + 1, array, index, currentSize - index - 1);
        currentSize--;
        return deletedValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(element, array[i])) {
                System.arraycopy(array, i + 1, array, i, currentSize - i - 1);
                currentSize--;
                return element;
            }
        }
        throw new NoSuchElementException("Can't find element");
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
}
