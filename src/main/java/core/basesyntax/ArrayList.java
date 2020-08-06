package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, currentSize);
    }

    @Override
    public void add(T value, int index) {
        checkSize();
        System.arraycopy(elementData, index, elementData, index + 1, currentSize - index);
        elementData[index] = value;
        currentSize++;
    }

    private void checkSize() {
        if (currentSize >= elementData.length) {
            int newSize = elementData.length * 3 / 2;
            T[] resultArray = (T[]) new Object[newSize];
            System.arraycopy(elementData, 0, resultArray, 0, elementData.length);
            elementData = resultArray;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexExist(index);
        return elementData[index];
    }

    private void isIndexExist(int index) {
        if (index < 0 || index >= currentSize) {
            throw new ArrayIndexOutOfBoundsException("Given index does not exist in this list!");
        }
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
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
        throw new NoSuchElementException("Element with given value does not exist in this List!");
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
