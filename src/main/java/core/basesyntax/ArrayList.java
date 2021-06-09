package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] arrayList;
    private int arrayListSize;

    public ArrayList() {
        arrayList = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int initialSize) {
        arrayList = (T[]) new Object[initialSize];
    }

    @Override
    public void add(T value) {
        if (arrayListSize >= arrayList.length) {
            grow();
        }
        arrayList[arrayListSize] = value;
        arrayListSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index == arrayListSize) {
            add(value);
            return;
        }
        if (indexIsNotValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("This index is not correct!");
        }
        if (index < arrayListSize) {
            slideForward(index);
        }
        arrayList[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int tempArrayListSize = arrayListSize;
        while (arrayList.length < (arrayListSize + list.size())) {
            grow();
        }
        for (int i = tempArrayListSize; i <= tempArrayListSize + list.size() - 1; i++) {
            arrayList[i] = list.get(i - tempArrayListSize);
            arrayListSize++;
        }
    }

    @Override
    public T get(int index) {
        if (indexIsNotValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("This index is not valid!");
        }
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (indexIsNotValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("This index is not valid!");
        }
        if (index == arrayList.length) {
            add(value);
            return;
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        T value;
        if (indexIsNotValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("This index is not valid!");
        }
        value = arrayList[index];
        slideBack(index);
        return value;
    }

    @Override
    public T remove(T element) {
        T value;
        for (int i = 0; i < arrayListSize; i++) {
            if (element == arrayList[i]
                    || (element != null && element.equals(arrayList[i]))) {
                value = arrayList[i];
                slideBack(i);
                return value;
            }
        }
        throw new NoSuchElementException("This element doesn't exist!");
    }

    @Override
    public int size() {
        return arrayListSize;
    }

    @Override
    public boolean isEmpty() {
        return arrayListSize == 0;
    }

    private boolean indexIsNotValid(int index) {
        return index >= arrayListSize || index < 0;
    }

    private void grow() {
        T[] tempArray = (T[]) new Object[(int) (arrayList.length * GROW_COEFFICIENT)];
        System.arraycopy(arrayList, 0, tempArray, 0, arrayList.length);
        arrayList = tempArray;
    }

    private void slideForward(int index) {
        if (arrayListSize + 1 > arrayList.length) {
            grow();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, arrayList.length - 1 - index);
        arrayListSize++;
    }

    private void slideBack(int index) {
        System.arraycopy(arrayList, index + 1, arrayList, index, arrayList.length - 1 - index);
        arrayListSize--;
    }
}
