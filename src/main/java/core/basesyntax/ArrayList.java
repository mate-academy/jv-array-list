package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] dataArray;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + initialCapacity);
        }
        dataArray = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        grow();
        dataArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkRangeAdd(index);
        if (size == dataArray.length) {
            grow();
        }
        System.arraycopy(dataArray, index, dataArray,
                index + 1, size - index);
        dataArray[index] = value;
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
        checkRange(index);
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        dataArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T oldPos = (T) dataArray[index];
        int newPos = size - index - 1;
        if (newPos > 0) {
            System.arraycopy(dataArray, index + 1, dataArray, index, newPos);
        }
        dataArray[--size] = null;
        return oldPos;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? dataArray[i] == null
                    : element.equals(dataArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Array does not have such element: " + element);
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
        if (size == dataArray.length) {
            int oldCapacity = dataArray.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(dataArray, 0, newArray, 0, size);
            dataArray = newArray;
        }
    }

    private void checkRangeAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index of element: " + index
                    + "Size of the list: " + size);
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index of element: " + index
                    + "Size of the list: " + size);
        }
    }
}
