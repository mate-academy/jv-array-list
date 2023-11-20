package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private final int capacity = 10;
    private final int changeStep = 1;
    private final int zeroIndex = 0;
    private T[] userArray;
    private int size;

    public ArrayList() {
        userArray = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        changeCapacity(size + changeStep);
        userArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < zeroIndex || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index of element");
        }
        changeCapacity(size + changeStep);
        System.arraycopy(userArray, index, userArray, index + changeStep, size - index);
        userArray[index] = value;
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
        if (index < zeroIndex || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index of element");
        } else {
            return userArray[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < zeroIndex || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index not exist");
        }
        userArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < zeroIndex || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index of element");
        }
        T removeElement = userArray[index];
        System.arraycopy(userArray, index + changeStep,
                userArray, index, size - index - changeStep);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        T removedElement;
        int indexRemove = findByValue(element);
        if (indexRemove != -1) {
            removedElement = userArray[indexRemove];
            remove(indexRemove);
        } else {
            throw new NoSuchElementException("Element not found");
        }
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void changeCapacity(int actualCapacity) {
        int oldCapacity = userArray.length;
        if (actualCapacity > oldCapacity) {
            int newCapacity = oldCapacity >> 1;
            if (newCapacity < actualCapacity) {
                newCapacity = actualCapacity;
            }
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(userArray, zeroIndex, newElements, zeroIndex, size);
            userArray = (T[]) newElements;
        }
    }

    private int findByValue(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && userArray[i] == null
                    || element != null && element.equals(userArray[i])) {
                return i;
            }
        }
        return -1;
    }
}
