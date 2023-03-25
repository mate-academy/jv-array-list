package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 10;
    private static final float FACTOR_ARRAY = 1.5f;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            increaseArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexOutOfBoundsAdd(index);
        if (size == array.length) {
            increaseArray();
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
        checkIndexOutOfBounds(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        T removed = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && array[i] == null) {
                return remove(i);
            } else if (element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArray() {
        T[] newArray = (T[]) new Object[(int) (array.length * FACTOR_ARRAY)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void checkIndexOutOfBoundsAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Array");
        }
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Array");
        }
    }
}
