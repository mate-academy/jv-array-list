package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_EXCEPTION_MESSAGE = "Index Out of Bound Exception";
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        provideSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            provideSize();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
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
        areIndexExist(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        areIndexExist(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        areIndexExist(index);
        T value = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        if (size == array.length) {
            T[] arrayWithANewSize = (T[]) new Object[size + (size / 2)];
            System.arraycopy(array, 0, arrayWithANewSize, 0, array.length);
            array = arrayWithANewSize;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean areIndexExist(int index) {
        if (!(index >= 0 && index < size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        return true;
    }

    private boolean provideSize() {
        if ((size + 1) >= array.length) {
            grow();
            return true;
        }
        return false;
    }
}
