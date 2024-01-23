package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[INITIAL_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        expandCapacityIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        expandCapacityIfArrayFull();
        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }
        elementData[index] = value;
        size++;

    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
        }
    }

    private void expandCapacityIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int) (elementData.length * RESIZE_FACTOR);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = (T[]) newArray;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); ++i) {
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
        checkIndexForSet(index);
        elementData[index] = value;
    }

    private void checkIndexForSet(int index) {
        if (index >= 0 && index <= size - 1) {
            return;
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no index: " + index);
        }
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            return remove(index);
        } else {
            throw new NoSuchElementException("There's no element: " + element);
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; ++i) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return i;
            }
        }
        return -1;
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
