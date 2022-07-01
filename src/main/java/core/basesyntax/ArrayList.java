package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH = 10;
    private static final double INCREASING_COEFFICIENT = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[START_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            growArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException(getExceptionMessage(index));
        } else if (index - this.size == 1) {
            add(value);
            return;
        } else if (size == elements.length) {
            growArray();
        }
        size++;
        addValueByIndex(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexNotExist(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexNotExist(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexNotExist(index);
        T[] newList = (T[]) new Object[(int)(elements.length - 1)];
        System.arraycopy(elements, 0, newList, 0, index);
        System.arraycopy(elements, index + 1, newList, index, newList.length - index);
        this.size--;
        T removedElement = elements[index];
        elements = newList;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == elements[i] || (element != null && (element.equals(elements[i])))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element " + element
                    + " doesn't exist in the List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void growArray() {
        T[] newList = (T[]) new Object[(int)(elements.length * INCREASING_COEFFICIENT)];
        System.arraycopy(elements, 0, newList, 0, elements.length);
        elements = newList;
    }

    private void addValueByIndex(T value, int index) {
        T[] newList = (T[]) new Object[elements.length];
        newList[index] = value;
        System.arraycopy(elements, 0, newList, 0, index);
        System.arraycopy(elements, index + 1 - 1, newList, index + 1, size() - (index + 1));
        elements = newList;
    }

    private void indexNotExist(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException(getExceptionMessage(index));
        }
    }

    private String getExceptionMessage(int index) {
        return "Invalid index " + index + " for size " + size;
    }
}
