package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_TO = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        addElement(value);
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        grow();
        addElement(value, index);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            grow();
            addElement(list.get(i));
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index + 1);
        T result = array[index];
        return result;
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T removedItem = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("The element is not present in the list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        if (array.length < size + 1) {
            T[] result = (T[]) new Object[(int) (array.length * GROW_TO) + 1];
            System.arraycopy(array, 0, result, 0, array.length);
            array = result;
        }
    }

    public void addElement(T value) {
        array[size] = value;
    }

    public void addElement(T value, int position) {
        T[] result = (T[]) new Object[array.length];
        System.arraycopy(array, 0, result, 0, position);
        result[position] = value;
        System.arraycopy(array, position, result, position + 1, size - position);
        array = result;
    }

    public void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }
    }

    public int getIndex(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }
}
