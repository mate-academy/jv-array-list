package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    public ArrayList() {
    }

    @Override
    public void add(T value) {
        resizeArray();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            resizeArray();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                                                         + " out of bounds for length ");
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
        if (index >= 0 && index < size) {
            return (T) array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                                                         + index + " out of bounds for length ");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                                                         + index + " out of bounds for length ");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T removeElement = (T) array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removeElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index "
                                                         + index + " out of bounds for length ");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Object = " + element + " doesn`t exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void resizeArray() {
        if (array.length == size) {
            Object[] newArray = new Object[array.length + (array.length / 2)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
