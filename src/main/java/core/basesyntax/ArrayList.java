package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index >= 0 && index <= size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bound");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bound");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bound");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T oldValue = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            size--;
            return (T) oldValue;
        } else {
            throw new ArrayListIndexOutOfBoundsException(index + "out of bound");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || (elementData[i] != null) && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element " + element + " is not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (elementData.length == size) {
            int newElementDataCapasity = (int) (elementData.length * 1.5);
            T[] newElementData = Arrays.copyOf(elementData, newElementDataCapasity);
            elementData = newElementData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index" + index);
        }
    }
}

