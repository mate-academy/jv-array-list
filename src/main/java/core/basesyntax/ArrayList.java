package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexRangeCheckForAdd(index);
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (listSize > elementData.length - size) {
            grow(size + listSize);
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexRangeCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexRangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexRangeCheck(index);
        T removedObject = (T) elementData[index];
        int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        elementData[size = newSize] = null;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element to remove");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void indexRangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void indexRangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        T[] grownArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, grownArray, 0, elementData.length);
        elementData = grownArray;
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = minCapacity + (oldCapacity / 2

        );
        T[] grownArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, grownArray, 0, elementData.length);
        elementData = grownArray;
    }
}
