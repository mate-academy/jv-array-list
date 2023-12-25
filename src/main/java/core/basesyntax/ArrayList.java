package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSize();
        array[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than zero");
        } else if (index < size) {
            size++;
            T[] tempArray = (T[]) new Object[checkLength()];
            for (int i = 0; i < index; i++) {
                tempArray[i] = array[i];
            }
            if (index < size) {
                for (int i = index + 1; i < tempArray.length; i++) {
                    tempArray[i] = array[i - 1];
                }
            }
            tempArray[index] = value;
            array = tempArray;
        } else if (index == size) {
            add(value);
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        T[] tempArray = (T[]) new Object[list.size()];
        int index = 0;
        while (index < list.size()) {
            tempArray[index] = list.get(index);
            index++;
        }
        resize(tempArray);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = array[index];
        array = getArrayAfterRemove(index);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        Integer indexToRemove = null;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null && element == null || array[i] != null
                    && array[i].equals(element)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == null) {
            throw new NoSuchElementException("No such element in array");
        }
        array = getArrayAfterRemove(indexToRemove);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size >= DEFAULT_SIZE) {
            resize();
        } else {
            size++;
        }
    }

    private int checkLength() {
        return size > array.length ? size : array.length;
    }

    private void resize() {
        size++;
        T[] tempArray = (T[]) new Object[size];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }

    private void resize(T[] newArray) {
        int tempSize = size + newArray.length;
        T[] tempArray = (T[]) new Object[tempSize];
        for (int i = 0; i < size(); i++) {
            tempArray[i] = array[i];
        }
        for (int y = 0; y < newArray.length; y++) {
            tempArray[size + y] = newArray[y];
        }
        size = tempArray.length;
        array = tempArray;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than zero");
        } else if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private T[] getArrayAfterRemove(int index) {
        size--;
        T[] tempArray = (T[]) new Object[array.length];
        for (int i = 0; i < index; i++) {
            tempArray[i] = array[i];
        }
        if (index < size) {
            for (int i = index; i < size + 1; i++) {
                tempArray[i] = array[i + 1];
            }
        }
        return tempArray;
    }
}
