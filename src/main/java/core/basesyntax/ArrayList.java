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
    private int lastElement;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        lastElement = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[lastElement++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexInBoundsCheck(index);
        System.arraycopy(elementData, index, elementData, index + 1, lastElement - index);
        elementData[index] = value;
        lastElement++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexInBoundsCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexInBoundsCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexInBoundsCheck(index);
        T valueHolder = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, lastElement - index - 1);
        elementData[--lastElement] = null;
        return valueHolder;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < lastElement; i++) {
            if (t == elementData[i] || (t != null && t.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + t + " not found in the array");
    }

    @Override
    public int size() {
        return lastElement;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void ensureCapacity() {
        if (lastElement + 1 >= elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void indexInBoundsCheck(int index) {
        if (index < 0 || index >= lastElement) {
            throw new ArrayIndexOutOfBoundsException("Illegal index: " + index + " .");
        }
    }
}
