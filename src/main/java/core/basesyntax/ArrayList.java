package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;
    private int size;
    private int currentCapacity;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == currentCapacity) {
            grow();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Specified index is invalid");
        }
        if (index == size) {
            add(value);
            return;
        }
        if (size == currentCapacity) {
            grow();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        checkIndex(index);
        @SuppressWarnings("unchecked")
        T element = (T) list[index];
        return element;
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = get(index);
        if (index != size - 1) {
            System.arraycopy(list, index + 1, list, index, size - index);
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int elIndex = indexOf(element);
        if (elIndex < 0) {
            throw new NoSuchElementException("Specified element is not in list");
        }
        T removedElement = get(elIndex);
        remove(elIndex);
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        currentCapacity += currentCapacity / 2;
        Object[] enlargedArray = new Object[currentCapacity];
        System.arraycopy(list, 0, enlargedArray, 0, size);
        list = enlargedArray;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (list[i] == null && element == null) {
                return i;
            }
            if (list[i] != null && list[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Specified index is invalid");
        }
    }
}
