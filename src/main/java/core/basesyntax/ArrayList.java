package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int INITIAL_ARRAY_SIZE = 10;
    private T[] arr;
    private int elementsCount = 0;

    public ArrayList() {
        arr = (T[]) new Object[INITIAL_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        checkIfNeedArrayResize();
        arr[elementsCount++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexCorrectWhileAddNewElements(index);
        checkIfNeedArrayResize();
        shiftArrayToRight(index);
        arr[index] = value;
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
        checkIfIndexCorrectInGeneralCase(index);
        return arr[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexCorrectInGeneralCase(index);
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexCorrectInGeneralCase(index);
        final T removedElement = arr[index];
        shiftArrayToLeft(index);
        arr[elementsCount - 1] = null;
        elementsCount--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < elementsCount; i++) {
                if (arr[i] == null) {
                    return remove(i);
                }
            }
        } else {
            int index = findCorrectIndex(element);
            if (index != -1) {
                return remove(index);
            }
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

    private void checkIfNeedArrayResize() {
        if (elementsCount == arr.length) {
            resizeArray();
        }
    }

    private void shiftArrayToRight(int shiftIndex) {
        checkIfNeedArrayResize();
        System.arraycopy(arr, shiftIndex, arr, shiftIndex + 1, elementsCount - shiftIndex);
    }

    private void shiftArrayToLeft(int shiftIndex) {
        System.arraycopy(arr, shiftIndex + 1, arr, shiftIndex, elementsCount - shiftIndex - 1);
    }

    private void resizeArray() {
        int newArrayCapacity = arr.length + 5;
        T[] resizedArray = (T[]) new Object[newArrayCapacity];
        System.arraycopy(arr, 0, resizedArray, 0, elementsCount);
        arr = resizedArray;
    }

    private void checkIfIndexCorrectInGeneralCase(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private void checkIfIndexCorrectWhileAddNewElements(int index) {
        if (index < 0 || index > elementsCount) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }

    private int findCorrectIndex(T element) {
        for (int i = 0; i < elementsCount; i++) {
            if (arr[i] != null && arr[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
