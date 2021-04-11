package core.basesyntax;

import java.util.NoSuchElementException;


public class ArrayList<T> implements List<T> {
    private final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    private final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    int size;
    int capacity;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add (list.get(i));
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
        T value = elementData[index];
        System.arraycopy(elementData,index + 1, elementData, index, size - index - 1);
        elementData[size--] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || elementData[i] != null && elementData[i].equals(element)) {
                T value = elementData[i];
                System.arraycopy(elementData,i + 1, elementData, i, size - i - 1);
                elementData[size--] = null;
                return value;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int index) {
        if (index < capacity) {
            return;
        }
        T[] tempData = (T[]) new Object[capacity];
        System.arraycopy(elementData,0, tempData, 0, size);
        int newCapacity = capacity << 1;
        if (newCapacity > MAX_CAPACITY) {
            newCapacity = MAX_CAPACITY;
        }
        elementData = (T[]) new Object[newCapacity];
        System.arraycopy(tempData,0, elementData, 0, size);
        capacity = newCapacity;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is out of array bounds");
        }
    }
}
