package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elementsData;
    private int size;

    public ArrayList() {
        elementsData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        resize();
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
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
        return elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementsData[i] || (elementsData[i] != null)
                    && elementsData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(" Index " + index + " out of bounds List");
        }
    }

    private void resize() {
        if (size == elementsData.length) {
            T[] resizeElementsArray = (T[]) new Object[(int) (elementsData.length * 1.5)];
            System.arraycopy(elementsData, 0, resizeElementsArray, 0, elementsData.length);
            elementsData = resizeElementsArray;
        }
    }
}
