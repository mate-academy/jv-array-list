package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAYLIST_SIZE = 10;
    private int size;

    private T[] array;

    public ArrayList() {
        array = (T[])(new Object[DEFAULT_ARRAYLIST_SIZE]);
    }

    @Override
    public void add(T value) {
        if (size + 1 > array.length) {
            growArray();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        outOfRangeCheck((index == size && size != 0) ? (index - 1) : index);
        if (size + 1 > array.length) {
            growArray();
        }
        System.arraycopy(array, index, array, index + 1,array.length - index - 1);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > array.length) {
            growArray();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        outOfRangeCheck(index == size ? index + 1 : index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        outOfRangeCheck(index == size ? index + 1 : index);
        array[index] = value;
        if (index > size) {
            size = index;
        }
    }

    @Override
    public T remove(int index) {
        outOfRangeCheck(index == size ? index + 1 : index);
        T result = array[index];
        System.arraycopy(array,index + 1, array, index,array.length - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == element || element != null && element.equals(array[i])) {
                remove(i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException();
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void growArray() {
        T[] tempArray = (T[])(new Object[array.length]);
        System.arraycopy(array, 0, tempArray,0, array.length);
        array = (T[])(new Object[array.length + (array.length >> 1)]);
        System.arraycopy(tempArray, 0, array,0, tempArray.length);
    }

    private void outOfRangeCheck(int index) {
        //if (size==0)
        if (index > size || index < 0 && size != 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array list range");
        }
    }
}
