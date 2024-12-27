package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_AFFIX = 1;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (isNotValidToAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException("Non existent "
                    + "position: " + index);
        }
        grow();
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
        if (isNotValidToFind(index)) {
            throw new ArrayListIndexOutOfBoundsException("Not found "
                    + "element at position: ");
        } else {
            return array[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (isNotValidToFind(index)) {
            throw new ArrayListIndexOutOfBoundsException("Not found "
                    + "element at position: " + index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isNotValidToFind(index)) {
            throw new ArrayListIndexOutOfBoundsException("Not found "
                    + "element at position: " + index);
        }
        T toDelete = array[index];
        removeAndTrim(index);
        return toDelete;
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
        T toDelete = array[index];
        removeAndTrim(index);
        return toDelete;
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
        if (size >= array.length) {
            T[] newArray = (T[]) new Object[array.length + array.length << GROWTH_AFFIX];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    boolean isNotValidToFind(int index) {
        return index < 0 || index >= size;
    }

    boolean isNotValidToAdd(int index) {
        return index < 0 || index > size;
    }

    boolean isLastIndex(int index) {
        return index + 1 == size;
    }

    void removeAndTrim(int index) {
        if (isLastIndex(index)) {
            array[index] = null;
        } else {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        size--;
    }
}
