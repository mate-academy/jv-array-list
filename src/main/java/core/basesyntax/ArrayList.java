package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = DEFAULT_CAPACITY;
    private int currentSize;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[size];
    }

    private T[] resizeTheArray(T[] array) {
        int newSize = (size * 3) / 2 + 1;
        T[] resultArray = (T[]) new Object[newSize];
        System.arraycopy(array, 0, resultArray, 0, size);
        size = newSize;
        return resultArray;
    }

    @Override
    public void add(T value) {
        add(value, currentSize);
    }

    @Override
    public void add(T value, int index) {
        if (currentSize >= size) {
            elementData = resizeTheArray(elementData);
        }
        System.arraycopy(elementData, index, elementData, index + 1, currentSize - index);
        elementData[index] = value;
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
        if (index < 0 || index >= currentSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < currentSize; i++) {
            if (i == index) {
                return elementData[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= currentSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = 0; i < currentSize; i++) {
            if (index == i) {
                elementData[i] = value;
                return;
            }
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= currentSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final T cup = elementData[index];
        if (currentSize - 1 - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, currentSize - 1 - index);
        }
        currentSize--;
        elementData[currentSize] = null;
        return cup;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(elementData[i], t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }
}
