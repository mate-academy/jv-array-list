package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWING_FACTOR = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
        growIfArrayFull();
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
        indexChecker(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecker(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecker(index);
        Object removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        int position = getIndex(element);
        if (position < 0) {
            throw new NoSuchElementException("element does not exist");
        }
        Object removedElement = remove(position);
        return (T) removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elementData[i])
                    || (element != null && element.equals(elementData[i]))) {
                return i;
            }
        }
        return -1;
    }

    private void growIfArrayFull() {
        if (elementData.length == size) {
            Object[] newArray = new Object[(int) (elementData.length * GROWING_FACTOR)];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    private void indexChecker(int index) {
        if ((index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }

    }
}
