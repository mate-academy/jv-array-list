package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float ARRAY_LENGTH_MULTIPLIER = 1.5f;
    private T[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity"
                    + initialCapacity);
        }
        elementData = (T[]) new Object[initialCapacity];
    }

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        int potentialSize = size + 1;
        if (potentialSize > elementData.length) {
            ensureCapacity(elementData.length);
        } else if (size == elementData.length) {
            ensureCapacity(elementData.length);
        }
        indexInBoundsCheck(index, potentialSize);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        indexInBoundsCheck(index,size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexInBoundsCheck(index,size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexInBoundsCheck(index, size);
        size--;
        Object oldObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,size - index);
        elementData[size] = null;
        return (T) oldObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in list, check again");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        elementData = Arrays.copyOf(elementData, (int)(minCapacity * ARRAY_LENGTH_MULTIPLIER));
    }

    private void indexInBoundsCheck(int index, int bounds) {
        if (index < 0 || index >= bounds || bounds < 0) {
            throw new ArrayListIndexOutOfBoundsException("Unreacheble index, please check again");
        }
    }
}
