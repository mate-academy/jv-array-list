package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index passed to the method is negative or "
                            + "bigger than size of ArrayList. "
                            + "Index: " + index + ", Size: " + size);
        }
        Object[] elementData;
        if (size == (elementData = this.elements).length) {
            grow();
            elementData = elements;
        }
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
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        int numberOfElements = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numberOfElements);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && elements[i] == null
                    || (element != null && element.equals(elements[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

//    private Object[] grow() {
    private void grow() {
        int oldCapacity = elements.length;
        if (oldCapacity > 0) {
            int newCapacity = (int) (oldCapacity * 1.5);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, oldCapacity);
            elements = newArray;
            return;
        }
        elements = new Object[DEFAULT_CAPACITY];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index passed to the method is negative or "
                            + "bigger than size of ArrayList. "
                            + "Index: " + index + ", Size: " + size);
        }
    }
}
