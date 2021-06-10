package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        arrayList = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        if (size >= arrayList.length) {
            grow();
        }
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        if (index < size) {
            slideForward(index);
        }
        arrayList[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int tempSize = size;
        while (arrayList.length < (size + list.size())) {
            grow();
        }
        for (int i = tempSize; i < tempSize + list.size(); i++) {
            arrayList[i] = list.get(i - tempSize);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = arrayList[index];
        slideBack(index);
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayList[i]
                    || (element != null && element.equals(arrayList[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element doesn't exist!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is not valid!");
        }
    }

    private void grow() {
        T[] tempArray = (T[]) new Object[(int) (arrayList.length * GROW_COEFFICIENT)];
        System.arraycopy(arrayList, 0, tempArray, 0, arrayList.length);
        arrayList = tempArray;
    }

    private void slideForward(int index) {
        if (size >= arrayList.length) {
            grow();
        }
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        size++;
    }

    private void slideBack(int index) {
        System.arraycopy(arrayList, index + 1, arrayList, index, size - 1 - index);
        size--;
    }
}
