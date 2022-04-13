package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private T[] elementData;
    private int size;
    private int actualSizeOfArray = DEFAULT_SIZE_OF_ARRAY;

    public ArrayList() {
        elementData = (T[]) new Object[actualSizeOfArray];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            actualSize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, please try again");
        } else if (size == elementData.length) {
            actualSize();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, please try again");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, please try again");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, please try again");
        }
        T elementRemoved = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return elementRemoved;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void actualSize() {
        int newSize = elementData.length + (elementData.length / 2);
        elementData = Arrays.copyOf(elementData, newSize);
    }
}
