package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (index < listLength && index >= 0) {
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
            int shift = this.listLength;
            for (int i = 0; i < list.size(); i++) {
                array[i + shift] = list.get(i);
            }
            listLength += list.size();
        }
    }

    @Override
    public T get(int index) {
        for (int i = 0; i < this.size(); i++) {
            if (i == index) {
                return array[i];
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + listLength);
    }

    @Override
    public void set(T value, int index) {
        for (int i = 0; i < listLength; i++) {
            if (i == index) {
                array[i] = value;
                return;
            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + listLength);
    }

    @Override
    public T remove(int index) {
        T value = null;
        for (int i = 0; i < listLength; i++) {
            if (i == index) {
                value = array[i];
                array[i] = null;
                for (int j = i + 1; j < listLength; j++) {
                    array[j - 1] = array[j];
                }
                array[listLength - 1] = null;
                listLength--;
                return value;

            }
        }
        throw new ArrayListIndexOutOfBoundsException("Index " + index
                + " out of bounds for length " + listLength);
    }

    @Override
    public T remove(T element) {
        int removedIndex = -1;
        T value = null;
        for (int i = 0; i < listLength; i++) {
            if (Objects.equals(array[i],element)) {
                value = array[i];
                array[i] = null;
                removedIndex = i;
                break;
            }
        }
        if (removedIndex == -1) {
            throw new NoSuchElementException("There is no such element in the list");
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
}
