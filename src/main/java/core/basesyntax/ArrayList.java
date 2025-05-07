package core.basesyntax;

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
        indexCheckForAdd(index);
        ensureCapacity();
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
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        size--;
        T oldObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,size - index);
        elementData[size] = null;
        return oldObject;
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

    private void ensureCapacity() {
        if (size == elementData.length) {
            T[] biggerArray = (T[]) new Object[(int)(elementData.length * ARRAY_LENGTH_MULTIPLIER)];
            System.arraycopy(elementData,0, biggerArray,0,size);
            elementData = biggerArray;
        }
    }

    private void indexCheckForAdd(int index) {
        if (index < 0 || index > size || size < 0) {
            throw new ArrayListIndexOutOfBoundsException("Unreacheble index, please check again");
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size || size < 0) {
            throw new ArrayListIndexOutOfBoundsException("Unreacheble index, please check again");
        }
    }
}
