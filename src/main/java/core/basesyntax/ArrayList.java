package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int listSize = 0;
    private int capacity = 10;
    private T[] arr;

    public ArrayList() {
        arr = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (listSize == capacity) {
            capacity = (int) (capacity * 1.5) + 1;
            T[] newArr = (T[]) new Object[capacity];
            for (int i = 0; i < listSize; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[listSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > listSize) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index);
        }

        if (listSize == capacity) {
            capacity = (int) (capacity * 1.5) + 1;
            T[] newArr = (T[]) new Object[capacity];

            for (int i = 0; i < index; i++) {
                newArr[i] = arr[i];
            }

            newArr[index] = value;

            for (int i = index; i < listSize; i++) {
                newArr[i + 1] = arr[i];
            }

            arr = newArr;
        } else {
            for (int i = listSize - 1; i >= index; i--) {
                arr[i + 1] = arr[i];
            }
            arr[index] = value;
        }

        listSize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int newSize = listSize + list.size();

        if (newSize > arr.length) {
            capacity = Math.max((int) (arr.length * 1.5) + 1, newSize);
            T[] newArr = (T[]) new Object[capacity];

            for (int i = 0; i < listSize; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        for (int i = 0; i < list.size(); i++) {
            arr[listSize + i] = list.get(i);
        }
        listSize = newSize;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= listSize) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index);
        }
        return arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= listSize) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index);
        } else {
            arr[index] = value;
        }

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= listSize) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index);
        }
        final T removedElement = arr[index];
        for (int i = index; i < listSize - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[listSize - 1] = null;
        listSize--;
        return removedElement;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < listSize; i++) {
            if (arr[i] == null && value == null) {
                final T removedElement = arr[i];
                for (int j = i; j < listSize - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[listSize - 1] = null;
                listSize--;
                return removedElement;
            }
            if (arr[i] != null && arr[i].equals(value)) {
                final T removedElement = arr[i];
                for (int j = i; j < listSize - 1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[listSize - 1] = null;
                listSize--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element not found: " + value);
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }
}
