package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayOfElements;
    private int size = 0;

    public ArrayList() {
        this.arrayOfElements = new Object[DEFAULT_CAPACITY];
    }

    private Object[] growIfArrayFull() {
        Object[] grownArray = arrayOfElements;
        if (size == arrayOfElements.length) {
            grownArray = new Object[(int) (arrayOfElements.length * 1.5)];
            System.arraycopy(arrayOfElements, 0, grownArray, 0, size);
        }
        return grownArray;
    }

    private void checkForValidIndexValue(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid value of index.");
        }
    }

    @Override
    public void add(T value) {
        arrayOfElements = growIfArrayFull();
        arrayOfElements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        arrayOfElements = growIfArrayFull();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid value of index.");
        }
        for (int i = size; i > index; i--) {
            arrayOfElements[i] = arrayOfElements[i - 1];
        }
        arrayOfElements[index] = value;
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
        checkForValidIndexValue(index);
        return (T) arrayOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkForValidIndexValue(index);
        arrayOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkForValidIndexValue(index);
        T removedValue = get(index);
        System.arraycopy(arrayOfElements, index + 1,
                arrayOfElements, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((arrayOfElements[i] != null && arrayOfElements[i].equals(element))
                    || arrayOfElements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element present in the list.");
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
