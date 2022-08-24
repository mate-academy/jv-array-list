package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        if (size > 0) {
            T[] newArray = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        } else {
            elementData = (T[]) new Object[10];
        }
    }

    @Override
    public void add(T value) {
        if (size() == elementData.length) {
            grow();
        }
        elementData[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index '" + index + "' is not valid.");
        }
        if (elementData.length == size) {
            grow();
        }
        System.arraycopy(elementData,index, elementData, index + 1, size - index);
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
        indexCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("element: '" + element + "' does not exist in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index '" + index + "' is not valid.");
        }
    }
}
