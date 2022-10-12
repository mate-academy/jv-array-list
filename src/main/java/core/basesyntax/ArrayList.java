package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double INCREASE_SIZE = 1.5;
    private int size;
    private Object[] array;

    public ArrayList() {
        this.size = 0;
        this.array = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            resize();
        }
        array[this.size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        if (array.length == size) {
            resize();
        }
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = value;//!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedArray = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return removedArray;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], element)) {
                return remove(i);
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

    private void resize() {
        int newSize = (int) (array.length * INCREASE_SIZE);
        Object[] resizedSize = new Object[newSize];
        System.arraycopy(array, 0, resizedSize, 0, array.length);
        this.array = resizedSize;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
