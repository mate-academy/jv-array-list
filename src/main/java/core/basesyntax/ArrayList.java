package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_LENGTH = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_ARRAY_LENGTH];
    }

    private void ensureSize() {
        if (size == array.length) {
            Object[] newArray = new Object[(int) (size * 1.5)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("There is no such element as " + index);
        }
    }

    @Override
    public void add(T value) {
        ensureSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            isIndexValid(index);
        }
        ensureSize();
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
        isIndexValid(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        Object remove = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return (T) remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == array[i]) || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
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
