package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    private static final double CAPACITY_MULTIPLIER = 1.5;

    private int size;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddMethod(index);
        resize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        int elementIndex = findElementIndex(element, array);
        if (elementIndex == -1) {
            throw new NoSuchElementException("Invalid element");
        }

        return remove(elementIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        if (size + 1 >= array.length) {
            int newCapacity = (int) (array.length * CAPACITY_MULTIPLIER);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    private int findElementIndex(T element, T[] innerArray) {
        for (int i = 0; i < innerArray.length; i++) {
            if ((innerArray[i] == null && element == null)
                    || (innerArray[i] != null && innerArray[i].equals(element))) {
                return i;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index at "
                    + index
                    + " where the size of the array is "
                    + size);
        }
    }

    private void checkIndexForAddMethod(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index at "
                    + index
                    + " where the size of the array is "
                    + size);
        }
    }
}
