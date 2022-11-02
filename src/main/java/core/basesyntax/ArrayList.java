package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    private static Object[] array = new Object[INIT_SIZE];
    private int size = 0;
    
    @Override
    public void add(T value) {
        if (size == array.length - 1) {
            increaseArray(array.length + array.length / 2 + 1);
        }
        array[size++] = value;
    }
    
    @Override
    public void add(T value, int index) {
        if (size != index) {
            checkIndex(index);
        }
        if (size == array.length - 1) {
            increaseArray(array.length + array.length / 2 + 1);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }
    
    @Override
    public void addAll(List<T> list) {
        Object[] a = new Object[list.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = list.get(i);
        }
        if (list.size() > array.length - size) {
            increaseArray(list.size() + size);
        }
        
        System.arraycopy(a, 0, array, size, list.size());
        size = list.size() + size;
    }
    
    @Override
    public T get(int index) {
        checkIndex(index);
        for (int i = 0; i < array.length; i++) {
            if (index == i) {
                return (T) array[i];
            }
        }
        return null;
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
        System.arraycopy(array, index + 1, array, index, size - index);
        array[size--] = null;
        return element;
    }
    
    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (element == null && null == array[i]) {
                System.arraycopy(array, i + 1, array, i, size - i);
                array[size--] = null;
                return null;
            }
            if (element != null && element.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i);
                array[size--] = null;
                return element;
            }
        }
        throw new NoSuchElementException("element is absant");
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void increaseArray(int s) {
        Object[] newArray = new Object[s];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
    
    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array");
        }
    }
}

