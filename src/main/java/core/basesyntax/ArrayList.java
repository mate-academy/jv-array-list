package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int listLength;

    @Override
    public void add(T value) {
        if (listLength < array.length) {
            array[listLength] = value;
            listLength++;
        } else {
            array = grow(array);
            array[listLength] = value;
            listLength++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + listLength);
        }
        if (index < array.length && index < listLength) {
            if (listLength == array.length) {
                array = grow(array);
            }
            for (int i = array.length - 1; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = value;
            listLength++;
        } else if (index == listLength) {
            this.add(value);
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + listLength);
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + listLength > array.length) {
            array = grow(array);
        }
        if (listLength == 0) {
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i);
                listLength++;
            }
        } else {
            int shift = indexOf(null);
            for (int i = 0; i < list.size(); i++) {
                array[i + shift] = list.get(i);
            }
            listLength += list.size();
        }
    }

    @Override
    public T get(int index) {
        T value = null;
        int selectedIndex = -1;
        for (int i = 0; i < this.size(); i++) {
            if (i == index) {
                value = array[i];
                selectedIndex = i;
            }
        }
        if (selectedIndex == -1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + listLength);
        }
        return value;
    }

    @Override
    public void set(T value, int index) {
        int updatedIndex = -1;
        for (int i = 0; i < listLength; i++) {
            if (i == index) {
                array[i] = value;
                updatedIndex = i;
            }
        }
        if (updatedIndex == -1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + listLength);
        }
    }

    @Override
    public T remove(int index) {
        int removedIndex = -1;
        T value = null;
        for (int i = 0; i < listLength; i++) {
            if (i == index) {
                value = array[i];
                array[i] = null;
                removedIndex = i;
            }
        }
        if (removedIndex == -1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + listLength);
        }
        for (int i = removedIndex + 1; i < listLength; i++) {
            array[i - 1] = array[i];
        }
        array[listLength - 1] = null;
        listLength--;
        return value;
    }

    @Override
    public T remove(T element) {
        int removedIndex = -1;
        T value = null;
        for (int i = 0; i < listLength; i++) {
            if (array[i] != null && array[i].equals(element)
                    || (array[i] == element)) {
                value = array[i];
                array[i] = null;
                removedIndex = i;
            }
        }
        if (removedIndex == -1) {
            throw new NoSuchElementException();
        }
        for (int i = removedIndex + 1; i < listLength; i++) {
            array[i - 1] = array[i];
        }
        array[listLength - 1] = null;
        listLength--;
        return value;
    }

    @Override
    public int size() {
        return listLength;
    }

    @Override
    public boolean isEmpty() {
        return listLength == 0;
    }

    private T[] grow(T[] originalArray) {
        int newSize = (int) Math.ceil(originalArray.length * 1.5);
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(originalArray, 0, newArray, 0, originalArray.length);
        return newArray;
    }

    private int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == o || (array[i] != null && array[i].equals(o))) {
                return i;
            }
        }
        return -1;
    }
}
