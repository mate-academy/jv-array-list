package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLIER = 1.5;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        Object[] updatedData = new Object[(int) (elementData.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(elementData, 0, updatedData, 0, size);
        elementData = new Object[(int) (updatedData.length * CAPACITY_MULTIPLIER)];
        System.arraycopy(updatedData, 0, elementData, 0, size);
    }

    @Override
    public void add(T value) {
        elementData[size] = value;
        size++;
        if (size == elementData.length) {
            grow();
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size | index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid.");
        }
        if (index == size) {
            elementData[index] = value;
            size++;
            return;
        }
        System.arraycopy(elementData, 0, elementData, 0, index);
        System.arraycopy(elementData, index, elementData, index + 1,
                elementData.length - (index + 1));
        elementData[index] = value;
        size++;
        if (size == elementData.length) {
            grow();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
            if (size == elementData.length) {
                grow();
            }
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 | index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid.");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size | index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid.");
        }
        this.elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 | index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid.");
        }
        Object removedElement = elementData[index];
        System.arraycopy(elementData, index + 1,
                elementData, index, elementData.length - (index + 1));
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        boolean elementFound = false;
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    | (elementData[i] != null && elementData[i].equals(element))) {
                System.arraycopy(elementData, 0, elementData, 0, i);
                System.arraycopy(elementData, i + 1, elementData, i, elementData.length - (i + 1));
                elementFound = true;
                break;
            }
        }
        if (!elementFound) {
            throw new NoSuchElementException("No such element exist.");
        }
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
