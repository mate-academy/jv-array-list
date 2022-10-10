package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIALIZATION_LENGTH = 10;
    private T[] array;
    private int size = 0;

    ArrayList() {
        array = (T[])new Object[INITIALIZATION_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            makeBiggerArray();
            array[size] = value;
            size++;
            return;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant add element"
                    + "for such index");
        }
        if (array.length == size) {
            makeBiggerArray();
        }
        System.arraycopy(array,index,array,index + 1, size - index);
        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant get element"
                    + "for such index");
        }
        return (T)array[index];
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant set element"
                    + "for such index");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cant remove element"
                    + "for such index to add");
        }
        size--;
        T returnValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null && element != null) {
                continue;
            }
            if (array[i] == null && element == null) {
                remove(i);
                return null;
            }
            if (array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't remove element for such"
                + " value " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public boolean checkIndex(int index) {
        return (index >= size || index < 0);
    }

    public void makeBiggerArray() {
        int newSize = (int) (size * 1.5);
        T[] newArray = (T[])new Object[newSize];
        System.arraycopy(array,0,newArray,0,array.length);
        array = newArray;
    }

    public static void main(String[] args) {
    }
}

