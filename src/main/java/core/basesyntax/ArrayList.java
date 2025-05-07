package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final int STANDART_CAPACITY = 10; // default size Arraylist
    private T[] array = (T[]) new Object[STANDART_CAPACITY]; //create array similar ArrayList
    private int size = 0; // counter elements in array

    private void checkCapacity() { // check Capacity and override array
        if (size == array.length) {
            T[] newArray = (T[]) new Object[array.length + (array.length / 2)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private void checkIndex(int index) { // check index of array
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index error :" + index);
        }
    }

    // methods similar ArrayList :

    @Override
    public void add(T value) {
        checkCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
        checkCapacity();
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
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return result;
    }

    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (null != element && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
