package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        resizeArray();
        array[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value."
                    + " No such index or index is less than 0");
        }
        resizeArray();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        } else {
            throw new RuntimeException("Can`t add this list, because list is null."
                    + " Try to use correct list");
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

    private void resizeArray() {
        if (size == array.length) {
            Object[] booferArray = new Object[array.length + (array.length >> 1)];
            System.arraycopy(array, 0, booferArray, 0, size);
            array = booferArray;
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value."
                    + " No such index or index is less than 0");
        }

    }

}


