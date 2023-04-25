package core.basesyntax;

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
        growIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexOutOfBoundsInclusive(index);
        growIfArrayFull();
        if (index != size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
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
        checkIndexOutOfBoundsExclusive(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOutOfBoundsExclusive(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBoundsExclusive(index);
        T removedElement = elementData[index];
        if (index != --size) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || elementData[i] != null && elementData[i].equals(element)) {
                removedElement = remove(i);
                return removedElement;
            }
        }
        checkNoSuchElementException(removedElement);
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

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int oldLength = size;
            int newLength = oldLength + (oldLength >> 1);
            T[] newData = (T[]) new Object[newLength];
            System.arraycopy(elementData, 0, newData, 0, size);
            elementData = newData;
        }
    }

    private void checkIndexOutOfBoundsInclusive(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index!Index: "
                    + index + ".Size: " + size);
        }
    }

    private void checkIndexOutOfBoundsExclusive(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index!Index: "
                    + index + ".Size: " + size);
        }
    }

    private void checkNoSuchElementException(T value) {
        if (value == null) {
            throw new NoSuchElementException("Invalid value!");
        }
    }
}
