package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    static final int MAX_SIZE = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[MAX_SIZE];
    }

    @Override
    public void add(T value) {
        if (array.length == size) {
            array = grow(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index do not exist");
        }
        if (array.length == size) {
            array = grow(array);
        }
        fastValueAdd(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexExistCheck(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexExistCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExistCheck(index);
        final T result = array[index];
        fastRemove(index);
        return result;
    }

    @Override
    public T remove(T element) {
        final T result;
        final int elementIndex;
        final int newSize = size - 1;

        for (int i = 0; i <= newSize; i++) {
            if (element == null) {
                if (array[i] == null) {
                    result = array[i];
                    elementIndex = i;
                    fastRemove(elementIndex);
                    return result;
                }
                if (i == newSize) {
                    throw new NoSuchElementException("Element do not exist");
                }
            }
            if (Objects.equals(array[i], element)) {
                result = array[i];
                elementIndex = i;
                fastRemove(elementIndex);
                return result;
            }
        }
        throw new NoSuchElementException("Element do not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    public T[] grow(T[] oldarray) {
        final int newLength = (int) (oldarray.length * 1.5);
        T[] newArray = (T[]) new Object[newLength];

        for (int i = 0; i < oldarray.length; i++) {
            newArray[i] = oldarray[i];
        }
        return newArray;
    }

    public void fastRemove(int index) {
        final int newSize = size - 1;

        System.arraycopy(array, index + 1, array, index, newSize - index);
        size--;
        array[size] = null;
    }

    public void indexExistCheck(int index) {
        final int newSize = size - 1;

        if (index < 0 || index > newSize) {
            throw new ArrayListIndexOutOfBoundsException("Index do not exist");
        }
    }

    public void fastValueAdd(T value, int index) {
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }
}
