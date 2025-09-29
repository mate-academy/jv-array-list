package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void resize() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * GROWTH_FACTOR);
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newData, 0, size);
        elementData = newData;
    }

    @Override
    public void add(T element) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = element;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == elementData.length) {
            resize();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        while (size + list.size() > elementData.length) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T element, int index) {
        checkIndex(index);
        elementData[index] = element;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removed = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                    elementData[size - 1] = null;
                    size--;
                    return null;
                }
            } else {
                if (elementData[i] != null && elementData[i].equals(element)) {
                    final T removed = elementData[i];
                    System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                    elementData[size - 1] = null;
                    size--;
                    return removed;
                }
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
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
