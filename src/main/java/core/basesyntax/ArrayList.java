package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_SHIFT = 1;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacityForAdd(1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        ensureCapacityForAdd(1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int additional = list.size();
        ensureCapacityForAdd(additional);
        for (int i = 0; i < additional; i++) {
            @SuppressWarnings("unchecked")
            T value = (T) list.get(i);
            elementData[size + i] = value;
        }
        size += additional;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        @SuppressWarnings("unchecked")
        T value = (T) elementData[index];
        return value;
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        int moveCount = size - index - 1;
        @SuppressWarnings("unchecked")
        T removed = (T) elementData[index];
        if (moveCount > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, moveCount);
        }
        elementData[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element to remove not found: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacityForAdd(int additionalCapacity) {
        int required = size + additionalCapacity;
        if (required <= elementData.length) {
            return;
        }
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> GROWTH_SHIFT);
        if (newCapacity < required) {
            newCapacity = required;
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: "
                    + index + ", size: " + size);
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds for add: "
                    + index + ", size: " + size);
        }
    }

    private int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
            return -1;
        }
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            T current = (T) elementData[i];
            if (element.equals(current)) {
                return i;
            }
        }
        return -1;
    }
}
