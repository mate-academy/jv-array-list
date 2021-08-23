package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private T[] arrayList;
    private int arrayListSize;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.arrayList = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (arrayListSize > 0 && arrayListSize == arrayList.length) {
            grow();
        }
        arrayList[arrayListSize] = value;
        arrayListSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > this.arrayListSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + ", Size: " + this.arrayListSize);
        }

        if (this.arrayListSize + 1 > arrayList.length) {
            grow();
        }
        expandArrayListByIndex(value, index);
        this.arrayListSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i <= list.size() - 1; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.arrayListSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + ", Size: " + this.arrayListSize);
        }
        return this.arrayList[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= this.arrayListSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " doesn't exist!");
        }
        arrayList[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.arrayListSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " doesn't exist!");
        }
        T removedElement = arrayList[index];
        narrowArrayListByIndex(index);
        arrayListSize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayList.length; i++) {
            if (Objects.equals(arrayList[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element: " + element + " doesn't exist!");
    }

    @Override
    public int size() {
        return arrayListSize;
    }

    @Override
    public boolean isEmpty() {
        return arrayListSize == 0;
    }

    private void grow() {
        this.arrayList = Arrays.copyOf(arrayList, arrayList.length + (arrayList.length >> 1));
    }

    private void expandArrayListByIndex(T value, int index) {
        @SuppressWarnings("unchecked")
        T[] newArrayList = (T[]) new Object[arrayList.length];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        newArrayList[index] = value;
        T[] tempArray = Arrays.copyOfRange(arrayList, index, this.arrayListSize);
        for (T tempValue : tempArray) {
            newArrayList[index + 1] = tempValue;
            index++;
        }
        arrayList = newArrayList;
    }

    private void narrowArrayListByIndex(int index) {
        @SuppressWarnings("unchecked")
        T[] newArrayList = (T[]) new Object[arrayList.length];
        System.arraycopy(arrayList, 0, newArrayList, 0, index);
        T[] tempArray = Arrays.copyOfRange(arrayList, index, this.arrayListSize);
        for (int i = 0; i < tempArray.length - 1; i++) {
            newArrayList[index] = tempArray[i + 1];
            index++;
        }
        arrayList = newArrayList;
    }
}
