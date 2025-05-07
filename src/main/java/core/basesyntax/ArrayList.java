package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_LIST_LENGTH = 10;
    private int size = 0;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[MAX_LIST_LENGTH];
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
        if (size == array.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T removedElement = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (element == null || element.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Invalid element " + element);
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
        int oldCapacity = array.length;
        T[] newArray;
        int newCapacity = oldCapacity * 3 / 2;
        newArray = Arrays.copyOf(array, newCapacity);
        array = newArray;
    }
}
