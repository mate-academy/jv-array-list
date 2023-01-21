package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_EMPTY_ELEMENT_DATA = new Object[DEFAULT_CAPACITY];
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = DEFAULT_EMPTY_ELEMENT_DATA;
    }

    //DONE
    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    //DONE
    @Override
    public void add(T value, int index) {
        checkRangeIndexForAdd(index);
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
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        }
        elementData[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int counter = 0;
        for (Object el : elementData) {
            if (equals(el, element)) {
                counter++;
            }
        }
        if (counter > 0) {
            int index = Arrays.asList(elementData).indexOf(element);
            remove(index);
        } else {
            throw new NoSuchElementException("There is no such element in list!");
        }
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of range " + size);
        }
    }

    private void checkRangeIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of range " + size);
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + Math.max(oldCapacity - minCapacity, oldCapacity >> 1);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
