package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int LENGTHS = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private int size;
    private Object[] elementData;

    private void initArrayIfNull() {
        if (elementData == null) {
            this.elementData = new Object[LENGTHS];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + ", but Size " + size);
        }
    }

    private void grow(int increment) {
        if (this.size + increment > elementData.length) {
            int newCapacity = Math.max((int) (elementData.length * GROWTH_FACTOR), this.size
                    + increment);
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
    }

    @Override
    public void add(T value) {
        initArrayIfNull();
        grow(1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + ", but Size " + size);
        }
        initArrayIfNull();
        grow(1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List is null");
        }
        if (list.isEmpty()) {
            return;
        }
        int needed = list.size();
        if (size + needed >= elementData.length) {

            int newCapacity = (elementData.length + elementData.length / 2);
            if (newCapacity < size + needed) {
                newCapacity = size + needed;
            }
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

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

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) elementData[index];

        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

        elementData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("This element doesn't exist");
        }

        T removed = (T) elementData[index];

        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removed;
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

