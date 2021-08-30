package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String OUT_OF_BOUNDS_MESSAGE = "Index out of bounds: ";
    private static final String NO_ELEMENT_MESSAGE = "No such element found!";
    private T[] arrayList;
    private int size;

    public ArrayList() {
        arrayList = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        sizeCheck();
        arrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        sizeCheck();
        System.arraycopy(arrayList, index, arrayList, index + 1, size - index);
        arrayList[index] = value;
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
        rangeCheck(index);
        return arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T removedElement = arrayList[index];
        System.arraycopy(arrayList, index + 1, arrayList, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(arrayList[i])) || arrayList[i] == element) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException(NO_ELEMENT_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        T[] grownArray = (T[]) new Object[arrayList.length * 3 / 2];
        System.arraycopy(arrayList, 0, grownArray, 0, size);
        arrayList = grownArray;
    }

    private void sizeCheck() {
        if (size == arrayList.length) {
            grow();
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE + index);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE + index);
        }
    }
}
