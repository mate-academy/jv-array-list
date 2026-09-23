package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void checkIndexArrayList(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    private void checkIndexArrayListForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            growIfArrayFull();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexArrayListForAdd(index);

        if (size == elementData.length) {
            growIfArrayFull();
        }

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }

        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    private void growIfArrayFull() {
        int newArrayCapacity = elementData.length + elementData.length / 2;
        T[] newArray = (T[]) new Object[newArrayCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    @Override
    public T get(int index) {
        checkIndexArrayList(index);
        return this.elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexArrayList(index);
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexArrayList(index);

        final T removedValue = this.elementData[index];

        for (int i = index; i < size - 1; i++) {
            this.elementData[i] = this.elementData[i + 1];
        }
        size--;
        elementData[size] = null;

        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
