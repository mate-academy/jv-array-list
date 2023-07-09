package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int listSize;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (listSize == elements.length) {
            resizeArrayList();
        }
        elements[listSize] = value;
        listSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size()) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        if (listSize == elements.length) {
            resizeArrayList();
        }
        System.arraycopy(elements, index, elements, index + 1, listSize - index);
        elements[index] = value;
        listSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        final T removedElement = (T)elements[index];
        System.arraycopy(elements, index + 1, elements, index, listSize - index - 1);
        elements[listSize - 1] = null;
        listSize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no element in this list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    private void resizeArrayList() {
        int newCapacity = listSize + (listSize >> 1);
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, listSize);
        elements = newElements;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size(); i++) {
            if (element == null && elements[i] == null) {
                return i;
            }
            if (elements[i] != null && element != null && element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void indexValidation(int index) {
        if (index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index can't "
                    + "be bigger than ArrayList size");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be less than 0");
        }
    }
}
