package core.basesyntax;

import edu.emory.mathcs.backport.java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object [] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkRange(index);
        if (elements.length == size) {
            increaseArraySize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listData = (T[]) new Object[list.size()];
        for (int i = 0; i < listData.length; i++) {
            listData[i] = list.get(i);
        }
        System.arraycopy(listData, 0, elements, size, listData.length);
        size += listData.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T removed = (T) elements[index];
        int numMoved = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[size--] = null;
        return removed;
    }

    @Override
    public T remove(T t) {
        int index = getExistingIndex(t);
        if (index >= 0) {
            return remove(index);
        }
        throw new NoSuchElementException("Element: " + t + " doesn't exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArraySize() {
        int newSize = (elements.length * 3) / 2 + 1;
        elements = Arrays.copyOf(elements, newSize);
    }

    private void checkRange(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This " + index
                                                    + " is out of range, size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("This index " + index
                                                    + " is out of bound" + "size: " + size);
        }
    }

    private int getExistingIndex(T value) {
        int index = 0;
        for (int i = 0; i < elements.length; i++) {
            if (value == elements[i] || (elements[i] != null && elements[i].equals(value))) {
                return index = i;
            }
        }
        return -1;
    }
}
