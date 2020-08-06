package core.basesyntax;

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
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        elementData = new Object[capacity];
    }

    private void ensureCapacity(int size) {
        while (size + 1 > elementData.length) {
            int newCapacity = (elementData.length * 3) / 2 + 1;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        Object[] arrayFromList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayFromList[i] = list.get(i);
        }
        System.arraycopy(arrayFromList, 0, elementData, size, list.size());
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        checkArrayIndexOutOfBoundsException(index);
        return (T) elementData[index];
    }

    private void checkArrayIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is not exists");
        }
    }

    @Override
    public void set(T value, int index) {
        checkArrayIndexOutOfBoundsException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        final Object removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size] = null;
        size--;
        return (T) removedValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null && elementData[i] == null || elementData[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element is not exists");
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
