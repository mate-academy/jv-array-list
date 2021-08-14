package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int maxSize = 10;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        array = new Object[maxSize];
    }

    private Object[] resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public void add(T value) {
        if (size == array.length - 1) {
            array = resize((int) (array.length * 1.5));
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("");
        }

        if (size == array.length - 1) {
            array = resize((int) (array.length * 1.5));
        }
        System.arraycopy(array, index, array, index + 1,
                size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size == array.length - 1) {
            array = resize((int) (array.length * 1.5));
        }
        Object[] newArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArray[i] = list.get(i);
        }
        System.arraycopy(newArray, 0, array, size, newArray.length);
        size += newArray.length;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        if (size == array.length - 1) {
            array = resize((int) (array.length * 1.5));
        }
        if (index < size) {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        T removedValue = (T) array[index];
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(array, index + 1, array, index, newSize - index);
        }
        array[size = newSize] = null;

        return removedValue;
    }

    @Override
    public T remove(T element) {
        T removedValue = null;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    removedValue = (T) array[i];
                    int newSize = size - 1;
                    if (newSize > i) {
                        System.arraycopy(array, i + 1, array, i, newSize - i);
                    }
                    array[size = newSize] = null;
                    return removedValue;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)) {
                removedValue = (T) array[i];
                int newSize = size - 1;
                if (newSize > i) {
                    System.arraycopy(array, i + 1, array, i, newSize - i);
                }
                array[size = newSize] = null;
            }
        }

        if (removedValue == null) {
            throw new NoSuchElementException("");
        }
        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
