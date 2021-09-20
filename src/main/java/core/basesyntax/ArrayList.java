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
        if (index >= 0 && index < size) {
            return elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            for (int i = 0; i < size; i++) {
                if (index == i) {
                    T removed = elementData[i];
                    System.arraycopy(elementData, i + 1, elementData, i, size - (i + 1));
                    size--;
                    return removed;
                }
            }
        }
        throw new ArrayListIndexOutOfBoundsException(index + " is invalid index");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == element
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
}
