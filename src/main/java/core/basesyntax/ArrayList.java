package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_LENGTH = 10;
    private T[] arrayList;
    private int arrayListSize;

    public ArrayList() {
        this.arrayList = (T[]) new Object[ARRAY_LENGTH];
        this.arrayListSize = 0;
    }

    @Override
    public void add(T value) {
        if (arrayListSize >= arrayList.length) {
            arrayList = growArray();
        }
        arrayList[arrayListSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > arrayListSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Element can't be added!");
        }
        if (arrayListSize >= arrayList.length) {
            arrayList = growArray();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1,
                arrayListSize - index);
        arrayList[index] = value;
        arrayListSize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == arrayList.length) {
            arrayList = growArray();
        }
        for (int i = 0; i < list.size(); i++) {
            arrayList[arrayListSize++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        isIndexExist(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexExist(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexExist(index);
        T result = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index,
                arrayList.length - index - 1);
        arrayListSize--;
        return result;
    }

    @Override
    public T remove(T t) {
        int indexRemove = Arrays.asList(arrayList).indexOf(t);
        if (indexRemove == -1) {
            throw new NoSuchElementException("Value is not a list");
        }
        return remove(indexRemove);
    }

    @Override
    public int size() {
        return arrayListSize;
    }

    @Override
    public boolean isEmpty() {
        return arrayListSize == 0;
    }

    private T[] growArray() {
        T[] temp = (T[]) new Object[(int) (arrayList.length * 1.5)];
        System.arraycopy(arrayList, 0, temp, 0, arrayListSize);
        return arrayList = temp;
    }

    private void isIndexExist(int index) {
        if (index < 0 || index >= arrayListSize) {
            throw new ArrayIndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + arrayListSize
                    + ". Total size of array = " + arrayList.length);
        }
    }
}
