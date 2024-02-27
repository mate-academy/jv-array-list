package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROWTH_INDEX = 1.5;
    private int size = 0;
    private T[] myArrayList = (T[]) new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {
        validateMyArrayListLength();
        myArrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateAddIndex(index);
        validateMyArrayListLength();

        //leave some comments because I think that code is hard to read & IDK how simplify
        if (index < size) {
            // new arr to hold copy of myArrayList before index
            T[] newArr = (T[]) new Object[myArrayList.length];
            newArr = copyToIndex(index, newArr);
            // add value to index
            newArr[index] = value;
            // add second first & second part with added value
            myArrayList = copyFromIndex(index, newArr);
            size++;
        }

        if (index == size) {
            myArrayList[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return myArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        myArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T[] newArr = (T[]) new Object[myArrayList.length];
        copyToIndexForRemove(index, newArr);
        T removedValue = myArrayList[index];
        myArrayList = copyFromIndexForRemove(index, newArr);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < myArrayList.length; i++) {
            if (isElementPresent(element, i)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateMyArrayListLength() {
        if (size == myArrayList.length) {
            myArrayList = increaseMyArrayListLength();
        }
    }

    private T[] increaseMyArrayListLength() {
        int newLength = (int) (myArrayList.length * GROWTH_INDEX);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(myArrayList, 0, newArray, 0, myArrayList.length);
        return newArray;
    }

    // Its happens to be hard for me to unite those index validations in one method
    private void validateAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Validation falls at add method");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Validation falls");
        }
    }

    private boolean isElementPresent(T element, int i) {
        if ((myArrayList[i] == null && element == null)
                || (myArrayList[i] != null && element != null && myArrayList[i].equals(element))) {
            return true;
        }
        return false;
    }

    private T[] copyToIndex(int index, T[] toArray) {
        System.arraycopy(myArrayList, 0, toArray, 0, index);
        return toArray;
    }

    private T[] copyFromIndex(int index, T[] toArray) {
        System.arraycopy(myArrayList, index, toArray, index + 1, size);
        return toArray;
    }

    private T[] copyToIndexForRemove(int index, T[] toArray) {
        System.arraycopy(myArrayList, 0, toArray, 0, index);
        return toArray;
    }

    private T[] copyFromIndexForRemove(int index, T[] toArray) {
        System.arraycopy(myArrayList, index + 1, toArray, index, myArrayList.length - size);
        return toArray;
    }
}
