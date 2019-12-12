package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public void ensureCapacity() {
        if (size >= elementData.length) {
            Object[] newElementData = Arrays.copyOf(elementData, size * 3 / 2 + 1);
            elementData = newElementData;
        }
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("" + index + "out of bound");
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        ensureCapacity();
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removedObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t != null && t.equals(elementData[i]) || t == elementData[i]) {
                return remove(i);

            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
