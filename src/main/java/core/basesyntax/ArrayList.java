package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            T[] tempArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, tempArray, 0, array.length);
            array = tempArray;
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index > size");
        }

        if (size + 1 > array.length) {
            T[] tempArray = (T[]) new Object[(int) (array.length * 1.5)];
            System.arraycopy(array, 0, tempArray, 0, index);
            tempArray[index] = value;
            System.arraycopy(array, index, tempArray, index + 1, size - index);
            array = tempArray;
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        }
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("list[index] = null");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        final T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size -= 1;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == null && array[i] == null
                    || element != null && element.equals(array[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException(element + "not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
}
