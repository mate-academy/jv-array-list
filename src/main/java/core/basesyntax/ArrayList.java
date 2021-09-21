package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int capacity;
    private int size;

    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
    }

    public ArrayList(int arrayListCapacity) {
        elementData = (T[]) new Object[arrayListCapacity];
        capacity = arrayListCapacity;
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            increaseCapacity();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
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
        T removed = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                T removed = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - (i + 1));
                size--;
                return removed;
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity() {
        if (size == elementData.length) {
            capacity = (capacity * 3) / 2 + 1;
            T[] tempElementData = (T[]) new Object[capacity];
            System.arraycopy(elementData, 0, tempElementData, 0, size);
            elementData = tempElementData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
        }
    }
}
