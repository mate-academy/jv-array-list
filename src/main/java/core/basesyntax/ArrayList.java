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
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        throwException(index, index > size || index < 0,
                new ArrayListIndexOutOfBoundsException("Can't add element in this index"));
        ensureCapacity(size + 1);
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
        throwException(index, index >= size || index < 0,
                new ArrayListIndexOutOfBoundsException("Element with this index doesn't exist"));
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        throwException(index, index >= size || index < 0,
                new ArrayListIndexOutOfBoundsException(
                        "Can't replace the element on the specified position"));
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        throwException(index, index >= size || index < 0,
                new ArrayListIndexOutOfBoundsException("Element with this index doesn't exist"));
        T removedElement = elementData[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        while (!(Objects.equals(elementData[index], element))) {
            index++;
            throwException(index, index == size,
                    new NoSuchElementException("There is no element like this in this list"));
        }
        removeElement(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        while (minCapacity >= elementData.length) {
            T[] newElementData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void removeElement(int index) {
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
    }

    private void throwException(int index, Boolean condition, RuntimeException e) {
        if (condition) {
            throw e;
        }
    }
}
