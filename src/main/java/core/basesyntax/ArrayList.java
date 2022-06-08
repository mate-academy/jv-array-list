package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[10];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);
        try {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to this index");
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        System.arraycopy(toArray(list), 0, elementData, size, list.size());
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element with this index doesn't exist");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't replace the element on the specified position");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        try {
            T removedElement = elementData[index];
            removeElement(index);
            return removedElement;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("There isn't element on this index");
        }
    }

    @Override
    public T remove(T element) {
        int index = 0;
        try {
            while (!(Objects.equals(elementData[index], element))) {
                index++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException("There is no such element in specified list");
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
        return elementData[0] == null;
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

    private T[] toArray(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
