package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAUL_CAPACITY = 10;
    private static final double GROWING_NUMB = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAUL_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resize();
        if (index != size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
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
        for (int i = 0; i < size; i++) {
            if (isEqual(array[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't delete this element, because it does not exsist.");
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    public boolean isEqual(T first, T second) {

        return first != null && first.equals(second) || first == second;
    }

    private void grow() {

        int newCapacity = (int) (size * GROWING_NUMB);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void resize() {
        if (array.length == size) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        }
    }
}
