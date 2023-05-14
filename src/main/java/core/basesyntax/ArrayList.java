package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLIER = 1.5;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        growIfSizeFull();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexVerifyForAdd(index);
        growIfSizeFull();

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
        indexVerify(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        indexVerify(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        indexVerify(index);
        T removedObject = array[index];
        System.arraycopy(array, index + 1, array, index,size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("wrong element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexVerify(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("wrong index " + index);
        }
    }

    private void indexVerifyForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("wrong index " + index);
        }
    }

    private void growIfSizeFull() {
        if (size == array.length) {
            int newLength = (int) (array.length * MULTIPLIER);
            Object[] newArray = new Object[newLength];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = (T[]) newArray;
        }
    }

}


