package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double KOEF_OF_GROW = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        capacityCheck();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexCheck(index);
        capacityCheck();
        System.arraycopy(array, index,
                array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            capacityCheck();
            array[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        size--;
        Object element = array[index];
        if (size > index) {
            System.arraycopy(array, index + 1,
                    array, index, size - index);
        }
        array[size] = null;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || element != null && element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        int newCapacity = (int) (array.length * KOEF_OF_GROW);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0,
                newArray, 0, size);
        array = newArray;
    }

    private void capacityCheck() {
        if (size == array.length) {
            grow();
        }
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid Index: " + index);
        }
    }
}
