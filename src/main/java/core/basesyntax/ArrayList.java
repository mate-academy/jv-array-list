package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_ARRAY = 10;
    private static final double INCREASE_SIZE_NUM = 1.5;
    private T[] elementArray;
    private int size;

    public ArrayList() {
        elementArray = (T[]) new Object[DEFAULT_SIZE_ARRAY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        elementArray[size] = value;
        ++size;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
        growIfFull();
        System.arraycopy(elementArray, index, elementArray, index + 1, size - index);
        elementArray[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); ++i) {
            add(list.get(i));
        }
    }

    private void increase() {
        int newSize = (int) (elementArray.length * INCREASE_SIZE_NUM);
        T[] increaseArray = (T[]) new Object[newSize];
        System.arraycopy(elementArray, 0, increaseArray, 0, elementArray.length);
        elementArray = increaseArray;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T rmElement = (T) elementArray[index];
        --size;
        System.arraycopy(elementArray, index + 1, elementArray, index, size - index);
        elementArray[size] = null;
        return rmElement;
    }

    @Override
    public T remove(T element) {
        return remove(findObject(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        if (size == elementArray.length) {
            increase();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds ");
        }
    }

    private void checkIndexAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds ");
        }
    }

    private int findObject(T object) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(elementArray[i], object)) {
                return i;
            }
        }
        throw new NoSuchElementException("This " + object + "does not exist in array");
    }
}
