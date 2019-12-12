package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[])new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity > 0) {
            elementData = (T[]) new Object[capacity];
        } else if (capacity == 0) {
            elementData = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + capacity);
        }
    }

    public void ensureCapacity() {
        Object[] arrayNewData = (T[])new Object[elementData.length * 3 / 2 + 1];
        System.arraycopy(elementData, 0, arrayNewData, 0, size);
        elementData = arrayNewData;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            ensureCapacity();
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
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " is wrong for using of this method");
        }
        return (T)elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " is wrong for using of this method");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index "
                    + index + " is wrong for using of this method");
        }
        T removedValue = (T)elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == elementData[i]
                    || (t != null && elementData[i] != null
                    && t.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element " + t + "in this list");
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
