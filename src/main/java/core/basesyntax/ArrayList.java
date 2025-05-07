package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] valuesArray;
    private int size = 0;

    public ArrayList() {
        valuesArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        int newCapacity = (int)(valuesArray.length * 1.5);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(valuesArray, 0, newArray, 0, size);
        valuesArray = newArray;
    }

    @Override
    public void add(T value) {
        if (size == valuesArray.length) {
            resize();
        }
        valuesArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }

        if (size == valuesArray.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            valuesArray[i] = valuesArray[i - 1];
        }
        valuesArray[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            return;
        }

        while (size + list.size() > valuesArray.length) {
            resize();
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        return valuesArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        valuesArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + "is out of bounds.");
        }
        final T removedValue = valuesArray[index];

        for (int i = index; i < size - 1; i++) {
            valuesArray[i] = valuesArray[i + 1];
        }
        valuesArray[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayList is empty.");
        }
        int indexToRemove = - 1;

        for (int i = 0; i < size; i++) {
            if ((element == null && valuesArray[i] == null)
                    || (element != null && element.equals(valuesArray[i]))) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1) {
            throw new NoSuchElementException("Element " + element + "not found.");
        }
        final T removedValue = valuesArray[indexToRemove];

        for (int j = indexToRemove; j < size - 1; j++) {
            valuesArray[j] = valuesArray[j + 1];
        }
        valuesArray[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
