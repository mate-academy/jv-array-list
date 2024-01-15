package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= values.length) {
            values = grow(values.length);
        }
        values[size] = backEmptyForNull(value);
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkBoundsIndex((index == size && size != 0) ? (index - 1) : index);
        int newSize = size + 1;
        if (newSize >= values.length) {
            values = grow(values.length);
        }
        T[] tempArray = (T[]) new Object[values.length];
        System.arraycopy(values,0, tempArray, 0, index);
        tempArray[index] = backEmptyForNull(value);
        System.arraycopy(values, index, tempArray, index + 1, size - index);
        values = tempArray;
        size = newSize;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkBoundsIndex(index);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return ((values[i].equals(EMPTY_ELEMENTDATA)) ? null : (T) values[i]);
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkBoundsIndex(index);
        for (int i = 0; i < size; i++) {
            if (i == index) {
                values[i] = backEmptyForNull(value);
            }
        }
    }

    @Override
    public T remove(int index) throws NoSuchElementException {
        checkBoundsIndex(index);
        T[] tempValues = (T[]) new Object[values.length];
        System.arraycopy(values, 0, tempValues, 0, index);
        System.arraycopy(values, index + 1, tempValues, index, size - index - 1);
        T oldValue = get(index);
        values = tempValues;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        T foundValue = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (backEmptyForNull(element).equals(backEmptyForNull(get(i)))) {
                index = i;
                foundValue = remove(index);
                break;
            }
        }
        checkElement(foundValue, index);
        return foundValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow(int oldCapacity) {
        int newCapacity = oldCapacity + oldCapacity / 2;
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, size);
        return newValues;
    }

    private T backEmptyForNull(T value) {
        return ((value == null) ? (T) EMPTY_ELEMENTDATA : value);
    }

    private void checkBoundsIndex(int index) {
        if ((index >= size && size != 0) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index = " + index
                    + " are not exist for this array with size " + size);
        }
    }

    private void checkElement(T value, int index) {
        if (index < 0 || index > size) {
            throw new NoSuchElementException("The element is not contained here: " + value);
        }
    }

}
