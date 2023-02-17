package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elementData;
    private int size;

    ArrayList() {
        elementData = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkSizeForAdd(index);
        System.arraycopy(elementData,index,elementData,index + 1, size  - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elementData.length - size) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element) || elementData[i] == element) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(elementData[i]);
            string.append(", ");
        }
        return string.toString();
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        Object[] temp = new Object[elementData.length];
        System.arraycopy(elementData, 0, temp, 0, elementData.length);
        elementData = Arrays.copyOf(temp, (int) Math.ceil(elementData.length * 1.5));
    }
    private void checkSizeForAdd(int index) {
        if (size == elementData.length) {
            grow();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The element at the given index does not exist.");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("The element at the given index does not exist.");
        }
    }
}
