package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_ARRAY_SIZE = 10;
    public static final double RESIZE_NUMBER = 1.5;

    private T[] initialArr;
    private int elementsCount;

    public ArrayList() {
        initialArr = (T[]) new Object[INITIAL_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        checkResize();
        initialArr[elementsCount++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        checkResize();
        shiftArrayToRight(index);
        initialArr[index] = value;
        elementsCount++;
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
        return initialArr[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        initialArr[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = initialArr[index];
        shiftArrayToLeft(index);
        initialArr[elementsCount - 1] = null;
        elementsCount--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findCorrectIndex(element);
        if (index != -1) {
            return remove(index);
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return elementsCount;
    }

    @Override
    public boolean isEmpty() {
        return elementsCount == 0;
    }

    private void checkResize() {
        if (elementsCount == initialArr.length) {
            resizeArray();
        }
    }

    private void shiftArrayToRight(int shiftIndex) {
        System.arraycopy(initialArr, shiftIndex, initialArr,
                shiftIndex + 1, elementsCount - shiftIndex);
    }

    private void shiftArrayToLeft(int shiftIndex) {
        System.arraycopy(initialArr, shiftIndex + 1, initialArr,
                shiftIndex, elementsCount - shiftIndex - 1);
    }

    private void resizeArray() {
        int newArrayCapacity = (int) (initialArr.length * RESIZE_NUMBER);
        T[] resizedArray = (T[]) new Object[newArrayCapacity];
        System.arraycopy(initialArr, 0, resizedArray, 0, elementsCount);
        initialArr = resizedArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > elementsCount) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private int findCorrectIndex(T element) {
        for (int i = 0; i < elementsCount; i++) {
            if (initialArr[i] == element || initialArr[i] != null
                    && initialArr[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
