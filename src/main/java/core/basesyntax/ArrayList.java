package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_INDEX];
    }

    public void exception() {
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds for length");
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            exception();
        }
        if (size == array.length) {
            grow();
        }
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
        if (index >= size || index < 0) {
            exception();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            exception();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            exception();
        }
        T oldValue = (T) array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T findElement;
        for (int i = 0; i <= size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                findElement = (T) array[i];
                remove(i);
                return findElement;
            }
        }
        throw new NoSuchElementException("There is no such element in this ArrayList");
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
        T[] newData = (T[]) array;
        array = new Object[size + (size >> 1)];
        System.arraycopy(newData, 0, array, 0, size);

    }
}
