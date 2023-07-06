package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureArrayCapacity();
        elementData[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        ensureArrayCapacity();
        ensureIndexInBounds(index, size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        size += 1;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        ensureIndexInBounds(index, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        ensureIndexInBounds(index, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        ensureIndexInBounds(index, size - 1);
        T removedElement = (T) elementData[index];
        size -= 1;
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (areBothValuesNull(elementData[i], element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureArrayCapacity() {
        if (size >= elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            this.elementData = newElementData;
        }
    }

    private void ensureIndexInBounds(int index, int bounds) {
        if (index > bounds || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private boolean areBothValuesNull(Object value, Object comparedValue) {
        return value == null && comparedValue == null;
    }
}
