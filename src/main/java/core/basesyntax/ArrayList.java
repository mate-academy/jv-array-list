package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_LENGTH = 10;
    private static final double GROW_NUMBER = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAY_LENGTH];;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            reSize();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index, 0);
        if ((index == size) || (size + 1) == array.length) {
            reSize();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] inputArray = ((ArrayList) (list)).array;
        int inputLength = list.size();
        if (inputLength >= array.length - size) {
            reSize();
        }
        System.arraycopy(inputArray, 0, array, size, inputLength);
        size += inputLength;
    }

    @Override
    public T get(int index) {
        checkIndexForAdd(index, 1);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForAdd(index, 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForAdd(index, 1);
        return fastRemove(index);
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        boolean foundElement = false;
        for (int i = 0; i <= size; i++) {
            if (element.equals(array[i])) {
                fastRemove(i);
                foundElement = true;
                break;
            }
        }
        if (!foundElement) {
            throw new NoSuchElementException("Element not found");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexForAdd(int index, int adjustment) {
        if (index > size - adjustment || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private T fastRemove(int index) {
        T elementToRemove = array[index];
        int lengthLastPartArray = size - index;
        int correctLastElement = lengthLastPartArray == 1 ? 1 : 0;
        System.arraycopy(array, index + 1, array, index, lengthLastPartArray - correctLastElement);
        size--;
        return elementToRemove;
    }

    private T[] reSize() {
        int currentSize = (int) (array.length * GROW_NUMBER);
        array = Arrays.copyOf(array, currentSize);
        return array;
    }
}
