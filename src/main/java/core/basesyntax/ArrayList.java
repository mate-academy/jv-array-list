package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private Object[] array = new Object[INITIAL_ARRAY_SIZE];
    private int size = 0;
    
    @Override
    public void add(T value) {
        resizeArray();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index value. "
                    + "No such index or index less than 0");
        }
        resizeArray();
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
        verificationOfIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        verificationOfIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        verificationOfIndex(index);
        resizeArray();
        T result = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || array[i] != null
                    && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resizeArray() {
        if (size == array.length) {
            Object[] boofer = new Object[array.length + (array.length >> 1)];
            System.arraycopy(array, 0, boofer, 0, size);
            array = boofer;
        }
    }

    private void verificationOfIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index value. "
                    + "No such index or index less than 0");
        }
    }
}

