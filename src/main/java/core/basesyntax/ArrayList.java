package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    private T[] tempMainArrayRightSide;
    private T[] tempMainArrayLeftSide;
    private T[] mainArray;
    private int length = 0;
    private int fullLength = 10;

    public ArrayList() {
        this.mainArray = (T[]) new Object[INITIAL_LENGTH];
    }

    @Override
    public void add(T value) {
        if (length == fullLength) {
            expandMainArray();
        }
        mainArray[length] = value;
        length++;
    }

    @Override
    public void add(T value, int index) {
        if (length == fullLength) {
            expandMainArray();
        }
        if (index > length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        int rightIndex = index;
        int leftIndex = length - index;
        splitMainArray(rightIndex, leftIndex, index, 0);
        mainArray = (T[]) new Object[++length];
        mainArray[index] = value;
        joinMainArray(rightIndex, leftIndex, index, 1);
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + length > fullLength) {
            expandMainArray();
        }
        for (int i = 0; i < list.size(); i++) {
            mainArray[length + i] = list.get(i);
        }
        length += list.size();
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return mainArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index);
        mainArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        int rightIndex = index;
        int leftIndex = length - index - 1;
        splitMainArray(rightIndex, leftIndex, index, 1);
        T elementToReturn = mainArray[index];
        mainArray = (T[]) new Object[--length];
        joinMainArray(rightIndex, leftIndex, index, 0);
        return elementToReturn;
    }

    @Override
    public T remove(T element) {
        return remove(getIndex(element));
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    private void splitMainArray(int rightIndex, int leftIndex, int index, int moveLeftIndexTo) {
        tempMainArrayRightSide = (T[]) new Object[rightIndex];
        tempMainArrayLeftSide = (T[]) new Object[leftIndex];
        System.arraycopy(mainArray, 0, tempMainArrayRightSide, 0, rightIndex);
        System.arraycopy(mainArray, index + moveLeftIndexTo, tempMainArrayLeftSide, 0, leftIndex);
    }

    private void joinMainArray(int rightIndex, int leftIndex, int index, int moveLeftIndexTo) {
        System.arraycopy(tempMainArrayRightSide, 0, mainArray, 0, rightIndex);
        System.arraycopy(tempMainArrayLeftSide, 0, mainArray, index + moveLeftIndexTo, leftIndex);
        tempMainArrayRightSide = null;
        tempMainArrayLeftSide = null;
    }

    private void expandMainArray() {
        int nextLength = fullLength + (fullLength >> 1);
        T[] tempMainArrayStore = (T[]) new Object[fullLength];
        for (int i = 0; i < fullLength; i++) {
            tempMainArrayStore[i] = mainArray[i];
        }
        mainArray = (T[]) new Object[nextLength];
        for (int i = 0; i < fullLength; i++) {
            mainArray[i] = tempMainArrayStore[i];
        }
        fullLength = nextLength;
    }

    private int getIndex(T value) {
        for (int i = 0; i < length; i++) {
            if ((mainArray[i] == null && value == null)
                    ^ (mainArray[i] != null && mainArray[i].equals(value))) {
                return i;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    private void checkIndexRange(int index) {
        if (index >= length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
