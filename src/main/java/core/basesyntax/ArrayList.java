package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_MIN_CAPACITY = 10;
    private Object[] array = new Object[ARRAY_MIN_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (index == array.length || size + 1 == array.length) {
            array = grow();
            copyArray(index);
        } else if (index < size) {
            copyArray(index);
        }
        array[index] = value;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }

    private void copyArray(int index) {
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
    }

    @Override
    public void addAll(List<T> list) {
        Object[] arr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
            add(list.get(i));
        }
    }

    private Object[] grow() {
        int oldCapacity = array.length;
        int newCapacity = (int) (oldCapacity * 1.5);
        return array = Arrays.copyOf(array, newCapacity);
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) array [index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not valid");
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        T oldValue;
        oldValue = value;
        array[index] = oldValue;

    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        final Object[] temp = array;
        T oldValue = (T) temp[index];
        shiftDown(temp, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue = null;
        int isElement = 0;
        for (int i = 0; i < size + 1; i++) {
            if ((array[i] != null && array[i].equals(element)
                    || (array[i] == null && array[i] == element))) {
                oldValue = remove(i);
                isElement++;
                break;
            }
        }
        if (isElement == 0) {
            throw new NoSuchElementException("");
        }
        return oldValue;
    }

    private void shiftDown(Object[] temp, int index) {
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(temp, index + 1, temp, index, newSize - index);
        }
        temp[size = newSize] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(array) + ", size=" + size();

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
