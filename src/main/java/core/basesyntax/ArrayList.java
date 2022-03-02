package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private Object[] array = new Object[MAX_ITEMS_NUMBER];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == array.length) {
            copyArray(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of Bounds!");
        }
        if (size == array.length) {
            copyArray(array);
        }
        Object[] tempArray = new Object[size + 1];
        System.arraycopy(array, 0, tempArray, 0, index);
        tempArray[index] = value;
        System.arraycopy(array, index, tempArray, index + 1, size - index);
        size++;
        array = tempArray;
    }

    @Override
    public void addAll(List<T> list) {
        while (true) {
            if (array.length - size < list.size()) {
                copyArray(array);
                continue;
            }
            break;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkOutBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutBounds(index);
        Object[] tempArray = new Object[size - 1];
        final Object result = array[index];
        System.arraycopy(array, 0, tempArray, 0, index);
        System.arraycopy(array, index + 1, tempArray, index, size - index - 1);
        array = tempArray;
        size--;
        return (T) result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || element != null && element.equals((T) array[i])) {
                Object[] tempArray = new Object[size - 1];
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void copyArray(Object[] oldOne) {
        Object[] newOne = new Object[array.length + array.length / 2];
        System.arraycopy(oldOne, 0, newOne, 0, oldOne.length);
        array = newOne;
    }

    private void checkOutBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of Bounds!");
        }
    }
}
