package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_RESIZE_VALUE = 1.5;
    private int changeableCapacity = 10;
    private int size = 0;
    private T[] elements = ((T[]) new Object[DEFAULT_CAPACITY]);

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Absent index in list: " + index);
        }
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
        ;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        checkSize();
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null && element == null
                    || elements[i] != null && elements[i].equals(element)) {
                indexOfElement = i;
                return remove(indexOfElement);
            }
        }
        throw new NoSuchElementException("There`s no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        changeableCapacity = (int) (changeableCapacity * DEFAULT_RESIZE_VALUE);
        T[] oldArray = elements;
        elements = (T[]) (new Object[changeableCapacity]);
        System.arraycopy(oldArray, 0, elements, 0, size);
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void checkSize() {
        if (size == changeableCapacity) {
            resize();
        }
    }
}
