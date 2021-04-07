package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;
    private Object[] itemsArray;

    public ArrayList() {
        itemsArray = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public ArrayList(int capacity) {
        itemsArray = new Object[capacity];
    }

    @Override
    public void add(T value) {
        capacityCheck();
        itemsArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        capacityCheck();
        System.arraycopy(itemsArray, index, itemsArray, index + 1, size - index);
        itemsArray[index] = value;
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
        indexCheck(index);
        return (T) itemsArray[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        itemsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        Object removedElement = itemsArray[index];
        System.arraycopy(itemsArray, index + 1, itemsArray, index, size - index);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (itemsArray[i] != null && itemsArray[i].equals(element)
                    || itemsArray[i] == element) {
                return remove(i);
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

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void capacityCheck() {
        if (size == capacity) {
            grow();
        }
    }

    private void grow() {
        capacity = capacity * 3 / 2;
        Object[] temporaryDataArray = new Object[capacity];
        System.arraycopy(itemsArray, 0, temporaryDataArray, 0, size);
        itemsArray = temporaryDataArray;
    }
}
