package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int ARRAY_CAPACITY = 10;
    private static final double INCREASE_INDEX = 1.5;
    private int arrSize;
    private T[] arrayElements;

    public ArrayList() {
        arrayElements = (T[]) new Object[ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        arrayElements[arrSize] = value;
        arrSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > arrSize) {
            throw new ArrayListIndexOutOfBoundsException("current index not found - index "
                    + index);
        }
        arrayElements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        checkSize();
        for (int i = 0; i < list.size(); i++) {
            arrayElements[arrSize] = list.get(i);
            arrSize++;
        }
    }

    @Override
    public T get(int index) {
        if (index > arrSize) {
            throw new ArrayListIndexOutOfBoundsException("current index not found - index "
                    + index);
        }
        return arrayElements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > arrSize) {
            throw new ArrayListIndexOutOfBoundsException("current index not found - index "
                    + index);
        }
        arrayElements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > arrSize) {
            throw new NoSuchElementException("can't remove element by index "
                    + index);
        }
        while (index != arrSize) {
            arrayElements[index] = arrayElements[index + 1];
            System.out.println(arrayElements[index]);
            index++;
        }
        return (T) arrayElements;
    }

    @Override
    public T remove(T element) {
        int check = 0;
        for (int i = 0; i < arrSize; i++) {
            if (arrayElements[i] != null && arrayElements[i].equals(element)) {
                remove(i);
                check++;
            }
        }
        if (check == 0) {
            throw new NoSuchElementException("can't find element - " + element);
        }
        return (T) arrayElements;
    }

    @Override
    public int size() {
        return arrSize;
    }

    @Override
    public boolean isEmpty() {
        return arrSize == 0;
    }

    private void checkSize() {
        if (arrSize == arrayElements.length) {
            grow();
        }
    }

    private T[] grow() {
        T[] newArr = (T[]) new Object[(int) (arrayElements.length * INCREASE_INDEX)];
        newArr = Arrays.copyOf(arrayElements, newArr.length);
        arrayElements = newArr;
        return newArr;
    }
}
