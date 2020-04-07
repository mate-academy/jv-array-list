package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int actualSize;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        actualSize = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[actualSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexBounds(index);
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, actualSize - index);
        elementData[index] = value;
        actualSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T valueHolder = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, actualSize - index - 1);
        elementData[--actualSize] = null;
        return valueHolder;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < actualSize; i++) {
            if (t == elementData[i] || (t != null && t.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element '" + t + "' not found in the array");
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void ensureCapacity() {
        if (actualSize + 1 >= elementData.length) {
            int increasedCapacity = elementData.length + (elementData.length >> 1);
            elementData = Arrays.copyOf(elementData, increasedCapacity);
        }
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= actualSize) {
            throw new ArrayIndexOutOfBoundsException("Illegal index: '" + index + "' .");
        }
    }
}
