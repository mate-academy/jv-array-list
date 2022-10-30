package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void checkingListToFullness() {
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData, size + size / 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't find element at index: " + index);
        }
    }

    private void removeElement(int index) {
        elementData[index] = null;
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
    }

    private int findIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(T value) {
        checkingListToFullness();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        checkingListToFullness();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element = (T) elementData[index];
        removeElement(index);
        return element;
    }

    @Override
    public T remove(T element) {
        int index = findIndexByElement(element);
        if (index == -1) {
            throw new NoSuchElementException("Can't find element by index" + index);
        }
        T removedElement = (T) elementData[index];
        removeElement(index);
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
}
