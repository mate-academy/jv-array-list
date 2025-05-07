package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double MULTIPLIER = 1.5;
    private Object[] elementsData;
    private int size;

    public ArrayList() {
        this.elementsData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSizeAndResize();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexOutOfBoundForAdd(index);
        checkSizeAndResize();
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
        checkIndexOutOfBound(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBound(index);
        elementsData[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBound(index);
        T deleteElement = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        size--;
        return deleteElement;
    }

    @Override
    public T remove(T element) {
        T removeElement = null;
        if (elementsData != null) {
            for (int i = 0; i < size; i++) {
                if ((element == null && elementsData[i] == null)
                        || (elementsData[i] != null && element != null
                        && element.equals(elementsData[i]))) {
                    removeElement = remove(i);
                    break;
                }
            }
        }
        if (element != null && removeElement == null) {
            throw new NoSuchElementException("This element is absent in elementsData " + element);
        }
        return removeElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexOutOfBoundForAdd(int index) {
        if (index < 0 || index >= size + 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid " + index);
        }
    }

    private void checkIndexOutOfBound(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid " + index);
        }
    }

    private void checkSizeAndResize() {
        if (size == elementsData.length) {
            Object[] newElementsData = new Object[(int) (elementsData.length * MULTIPLIER)];
            System.arraycopy(elementsData, 0, newElementsData, 0, size);
            elementsData = newElementsData;
        }
    }
}
