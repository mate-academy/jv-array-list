package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            resize();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        T[] listToArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listToArray[i] = list.get(i);
            add(listToArray[i]);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elementData[index];
        if (index == (size - 1)) {
            elementData[index] = null;
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[size - 1] = null;
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                T removedElement = elementData[i];
                remove(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("Object not present in list");
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
        if (size == elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range for size " + size);
        }
    }
}

