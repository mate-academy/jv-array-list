package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        array[size] = value;
        addSize();
    }

    @Override
    public void add(T value, int index) {
        checkCorrectIndex(index);
        addSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkCorrectIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkCorrectIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkCorrectIndex(index);
        return removeElement(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return removeElement(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCorrectIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Error");
        }
    }

    private void addSize() {
        size++;
        if (size >= array.length) {
            Object[] extendedArray = new Object[array.length + (array.length / 2)];
            System.arraycopy(array, 0, extendedArray, 0, array.length);
            array = (T[]) extendedArray;
        }
    }

    private T removeElement(int index) {
        T value = (T) array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return value;
    }
}
