package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private T [] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private T [] grow() {
        return elementData = Arrays.copyOf(elementData,
                elementData.length + (elementData.length >> 1));
    }

    private void checkIndex(int index, int size) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not valid: index: "
            + index + ", size: " + size);
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);
        if (size == elementData.length) {
            elementData = grow();
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
        checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T oldElement = elementData [index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        int index;
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || element != null && (element.equals(elementData[i]))) {
                index = i;
                return remove(index);
            }
        }
        throw new NoSuchElementException("Can`t find this element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
