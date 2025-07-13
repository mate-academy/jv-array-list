package core.basesyntax;

import java.util.NoSuchElementException;
//import core.basesyntax.ArrayListIndexOutOfBoundsException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private Object[] elementData = new Object[DEFAULT_SIZE];

    @Override
    public void add(T value) {

        if (size == elementData.length) {
            growList(1);
        }

        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }

        if (size == elementData.length) {
            growList(1);
        }

        Object[] tampArr = new Object[size - index];

        for (int i = index,k = 0; k < tampArr.length; i++,k++) {
            tampArr[k] = elementData[i];
        }

        elementData[index] = value;
        for (int i = index + 1,k = 0;i < size + 1;i++,k++) {
            elementData[i] = tampArr[k];
        }

        size++;

    }

    @Override
    public void addAll(List<T> list) {

        int addRange = list.size();

        if (size + addRange > elementData.length) {
            growList(addRange);
        }

        int addSize = list.size();
        Object[] source = list.toArray(addSize);
        int newSize = size + addSize;
        for (int i = size,k = 0;i < newSize;i++,k++) {
            elementData[i] = source[k];
        }
        size = newSize;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }

        return (T) elementData[index];

    }

    @Override
    public void set(T value, int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }

        elementData[index] = value;

    }

    @Override
    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is invalid");
        }

        Object[] tampArr = new Object[size - (index + 1)];

        for (int i = (index + 1),k = 0;k < tampArr.length;i++,k++) {
            tampArr[k] = elementData[i];
            elementData[i] = null;
        }

        T value = (T) elementData[index];
        for (int i = index,k = 0;i < size - 1;i++,k++) {
            elementData[i] = tampArr[k];
        }

        size--;

        return value;

    }

    @Override
    public T remove(T element) {

        Object oldValue = null;

        for (int i = 0; i < size; i++) {

            int index = -1;

            if (element == null && elementData[i] == null) {
                index = i;
            } else if (elementData[i] != null && elementData[i].equals(element)) {
                index = i;
            } else {
                continue;
            }

            if (index >= 0) {

                oldValue = elementData[index];

                Object[] tampArr = new Object[size - (index + 1)];

                for (int s = (index + 1),k = 0;k < tampArr.length;s++,k++) {
                    tampArr[k] = elementData[s];
                    elementData[s] = null;
                }

                for (int t = index,k = 0;t < size - 1;t++,k++) {
                    elementData[t] = tampArr[k];
                }

                size--;

                return (T) oldValue;
            }

        }

        if (oldValue == null) {
            throw new NoSuchElementException("Element don`t found");
        }

        return null;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void growList(int addRange) {

        if (elementData.length < (size + addRange)) {
            int newSize = Math.max((elementData.length + (elementData.length >> 1)),
                              (size + addRange));
            Object[] tempArr = new Object[newSize];
            for (int i = 0;i < size;i++) {
                tempArr[i] = elementData[i];
            }
            elementData = tempArr;
        }

    }

    @Override
    public Object[] toArray(int newSize) {

        Object[] newArr = new Object[newSize];
        for (int i = 0;i < newSize; i++) {
            newArr[i] = elementData[i];
        }

        return newArr;

    }
}
