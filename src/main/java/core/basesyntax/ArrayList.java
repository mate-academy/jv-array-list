package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity > 0) {
            array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
    }

    @Override
    public void add(T value) {
        if (size >= array.length) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Non existent "
                    + "position: " + index);
        }
        if (size >= array.length) {
            grow();
        }
        Object[] result = new Object[++size];
        copy(array, 0, result, 0, index);
        result[index] = value;
        copy(array, index, result, index + 1, size - index - 1);
        array = result;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isNotValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Not found "
                    + "element at position: ");
        } else {
            return (T) array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (isNotValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Not found "
                    + "element at position: " + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isNotValidIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Not found "
                    + "element at position: " + index);
        }
        Object toDelete = array[index];
        removeAndTrim(index);
        return (T) toDelete;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null ? array[i] == element
                    : array[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Not found "
                    + "element at position: " + index);
        }
        Object toDelete = array[index];
        removeAndTrim(index);
        return (T) toDelete;
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
        Object[] newArray = new Object[array.length + array.length / 2];
        copy(array, 0, newArray, 0, size);
        array = newArray;
    }

    boolean isNotValidIndex(int index) {
        return index < 0 || index >= size;
    }

    void removeAndTrim(int index) {
        Object[] newArray = new Object[size--];
        copy(array, 0, newArray, 0, index);
        copy(array, index + 1, newArray, index, size - index);
        array = newArray;
    }

    void copy(Object[] source, int srcIndex, Object[] destination, int destIndex, int length) {
        try {
            for (int i = srcIndex, j = 0; i < srcIndex + length && j < length; i++, j++) {
                destination[destIndex + j] = source[i];
            }
        } catch (RuntimeException e) {
            throw new IndexOutOfBoundsException("Index is out the array bond");
        }
    }
}
