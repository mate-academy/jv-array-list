package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] array = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == array.length) {
            array = copyArray(array);
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
            array = copyArray(array);
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
                array = copyArray(array);
                continue;
            }
            break;
        }
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of Bounds!");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of Bounds!");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Out of Bounds!");
        }
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
                if (i == 0) {
                    System.arraycopy(array, 1, tempArray, 0, size - 1);
                } else if (i == size - 1) {
                    System.arraycopy(array, 0, tempArray, 0, size - 1);
                } else {
                    System.arraycopy(array, 0, tempArray, 0, i);
                    System.arraycopy(array, i + 1, tempArray, i, size - i - 1);
                }
                array = tempArray;
                size--;
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

    private Object[] copyArray(Object[] oldOne) {
        Object[] newOne = new Object[array.length + array.length / 2];
        System.arraycopy(oldOne, 0, newOne, 0, oldOne.length);
        return newOne;
    }
}
