package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double STEP_GROWTH = 1.5;

    private int size;
    private T[] ownArrayList;

    public ArrayList() {
        ownArrayList = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= ownArrayList.length) { //(size >= newArraySize)
            resizeArray();
        }
        ownArrayList[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkForValidIndex(index);
        if (size >= ownArrayList.length) {
            resizeArray();
        }
        System.arraycopy(ownArrayList, index, ownArrayList, index + 1, size - index);
        ownArrayList[index] = value;
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
        checkForValidIndex(index);
        return ownArrayList[index];
    }

    @Override
    public void set(T value, int index) {
        checkForValidIndex(index);
        ownArrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForValidIndex(index);
        T value = ownArrayList[index];
        removeElementByIndex(index);
        return value;
    }

    @Override
    public T remove(T element) {
        int occurance = 0;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (element == null || element.equals(ownArrayList[i])) {
                index = i;
                occurance++;
            }
        }
        if (occurance == 0) {
            throw new NoSuchElementException("The element doesn't exist");
        }
        T value = ownArrayList[index];
        removeElementByIndex(index);
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkForValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of range."
                    + " Current array size is " + size + ". Please, enter correct index");
        }
    }

    private void removeElementByIndex(int index) {
        System.arraycopy(ownArrayList, index + 1, ownArrayList, index, size - index - 1);
        size--;
    }

    public void resizeArray() {
        T[] arrayCopy = (T[]) new Object[(int) (ownArrayList.length * STEP_GROWTH)];
        System.arraycopy(ownArrayList, 0, arrayCopy, 0, size);
        ownArrayList = arrayCopy;
    }
}
