package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final double INDEX_TO_INCREASE_THE_SIZE = 1.5;
    private int size;
    private T [] array;

    public ArrayList() {
        array = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    @Override
    public void add(T value) {
        resize();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Not correct index " + index);
        }
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
        T remove = array[index];
        System.arraycopy(array,index + 1,array, index, size - index);
        size--;
        return remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                System.arraycopy(array,i + 1,array, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Index out of size");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == array.length) {
            T[] newArray = (T[]) new Object[(int) (array.length * INDEX_TO_INCREASE_THE_SIZE)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Not correct index " + index);
        }

    }
}
