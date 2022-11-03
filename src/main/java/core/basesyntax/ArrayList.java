package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] array;
    private int size;
    
    public ArrayList() {
        this.array = new Object[INITIAL_SIZE];
    }
    
    @Override
    public void add(T value) {
        if (size == array.length) {
            increaseArray();
        }
        array[size] = value;
        size++;
    }
    
    @Override
    public void add(T value, int index) {
        if (size == array.length) {
            increaseArray();
        }
        if (size != index) {
            checkIndex(index);
        }
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
        return (T) array[index];
    }
    
    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }
    
    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = (T) array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        array[--size] = null;
        return element;
    }
    
    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == array[i] || element != null && element.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i);
                array[--size] = null;
                return element;
            }
        }
        throw new NoSuchElementException("element is absant value : " + element);
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void increaseArray() {
        int newCapacity = array.length + array.length / 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
    
    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of array. Size: " + size);
        }
    }
}

