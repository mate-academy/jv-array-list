package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_MULTIPLIER = 1.5;
    private int capacity;
    private T[] elementData;
    private int size;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elementData = (T[]) new Object[capacity];
    }

    private void resizeIfNeeded() {
        if (size == capacity) {
            capacity *= DEFAULT_MULTIPLIER;
            T[] expandedArray = (T[]) new Object[capacity];
            System.arraycopy(elementData, 0, expandedArray, 0, size);
            elementData = expandedArray;
        }
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of ArrayList bound!");
        }
    }

    public boolean isEquals(Object first, Object second) {
        return first == second || first != null && first.equals(second);
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        resizeIfNeeded();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > capacity) {
            resizeIfNeeded();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index,
                size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such an element does not exist!");
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
