package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_MULTIPLICATION = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        exceptionIndex(index);
        if (elementData.length == size) {
            grow();
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
        exceptionIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        exceptionIndex(index);
        Object remove = elementData[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        }
        size--;
        return (T) remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && element == null
                    || elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        int length = (int) (size * GROW_MULTIPLICATION);
        Object[] arr = new Object[length];
        System.arraycopy(elementData, 0, arr, 0, size);
        return elementData = arr;
    }

    private void exceptionIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }
}
