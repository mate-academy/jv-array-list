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
        if (index >= 0 && index < (size + 1)) {
            resizeArray();
            System.arraycopy(array, index, array, index + 1, size - index);
            /** OR
             * for (int i = size; i > index; i--) {
             *      array[i] = array[i - 1];
             * }
             * */
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                                                         + " out of bounds for length ");
        }
    }

    @Override
    public void addAll(List<T> list) {
        System.arraycopy(list, 0, array, size - 1, size + list.size());
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
        T removeElement = null;
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                removeElement = (T) array[i];
                System.arraycopy(array, i, array, i + 1, size - 1);
            } else {
                throw new NoSuchElementException();
            }
        }
        return removeElement;
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
            /** OR
             * for (int i = 0; i < ; i++) {
             * }
             */
            array = newArray;
        }
    }

    private int getCurentIndex(T value) {
        if (value == null) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return size;
    }
}
