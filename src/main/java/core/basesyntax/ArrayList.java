package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_FACTOR = 1.5;

    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }

        checkBounds(index);
        growIfArrayFull();
        int elementsToShift = ++size - index - 1;
        System.arraycopy(array, index, array, index + 1, elementsToShift);
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        ensureCapacity(listSize);

        for (int i = 0; i < listSize; i++) {
            add(list.get(i));
        }
    }

    private void ensureCapacity(int listLength) {
        while (array.length < size + listLength) {
            grow();
        }
    }

    private void growIfArrayFull() {
        if (size == array.length) {
            grow();
        }
    }

    private void grow() {
        int resizedCapacity = (int) (array.length * RESIZE_FACTOR);
        T[] tempCopyOfArray = (T[]) new Object[resizedCapacity];
        System.arraycopy(array, 0, tempCopyOfArray, 0, array.length);
        array = (T[]) new Object[resizedCapacity];
        System.arraycopy(tempCopyOfArray, 0, array, 0, array.length);
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T objectRemoved = array[index];
        int elementsToShift = size - index - 1;
        System.arraycopy(array, index + 1, array, index, elementsToShift);
        array[--size] = null;
        return objectRemoved;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T targetElement = array[i];

            if (targetElement == element
                    || targetElement != null && targetElement.equals(element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element " + element + " does not exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkBounds(int index) {
        if (index < 0 || (index > size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds.");
        }
    }
}
