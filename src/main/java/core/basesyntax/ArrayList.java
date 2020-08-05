package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int capacity;
    private int size = 0;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        elementData = new Object[capacity];
    }

    private void ensureCapacity(int size) {
        if (size > elementData.length) {
            capacity = (capacity * 3) / 2 + 1;
            Object[] newArray = new Object[capacity];
            System.arraycopy(elementData, 0, newArray, 0, size - 1);
            elementData = newArray;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);
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
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is not exists");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index is not exists");
        }
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
            if (i == size - 1) {
                throw new NoSuchElementException("Element is not exists");
            }
        }
        return null;
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
