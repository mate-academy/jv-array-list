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

    @Override
    public void add(T value) {
        checkBeforeGrow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "the index is not valid index: " + index + " size: " + size);
        }
        checkBeforeGrow();
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
        outOfBoundsCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBeforeGrow();
        outOfBoundsCheck(index);
        T result = elementData[index];
        dataTransferWhenRemove(index + 1, index);
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exists");
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
        int incrementStep = elementData.length / 2;

        T[] arrayListTemp = (T[]) new Object[elementData.length + incrementStep];
        System.arraycopy(elementData, 0, arrayListTemp, 0, size);
        elementData = arrayListTemp;
    }

    private void outOfBoundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "the index is not valid index: " + index + " size: " + size);
        }
    }

    private void dataTransferWhenRemove(int index1, int index2) {
        System.arraycopy(elementData, index1, elementData, index2, size - index2);
        elementData[--size] = null;
    }

    private void checkBeforeGrow() {
        if (elementData.length == size) {
            grow();
        }
    }
}
