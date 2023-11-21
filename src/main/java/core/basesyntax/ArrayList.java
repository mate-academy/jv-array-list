package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITYY = 10;
    private static final double INCREMENT_INDEX = 1.5;
    private T[] array;
    private int size;

    ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITYY];
    }

    @Override
    public void add(T value) {
        resizeArrayIfFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
        resizeArrayIfFull();
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
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int i = 0;
        for (Object elementFromArr : array) {
            if (elementFromArr == element || (elementFromArr != null
                    && elementFromArr.equals(element))) {
                return remove(i);
            }
            i++;
        }
        throw new NoSuchElementException("There is no such element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArrayIfFull() {
        if (array.length == size) {
            T[] newArray = (T[]) new Object[(int) (size * INCREMENT_INDEX)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("You can't add element to index: "
                    + index);
        }
    }
}
