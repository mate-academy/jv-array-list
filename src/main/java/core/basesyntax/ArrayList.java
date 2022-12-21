package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double COEFFICIENT_GROW = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        checkAndGrow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkAndGrow();
            add(list.get(i));
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
        final Object[] withoutChanges = elementData;
        T oldValue = (T) withoutChanges[index];
        fastRemove(withoutChanges, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(elementData[i])) || (element == null && element
                    == elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such elements here:" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int i) {
        if (i >= size || i < 0) {
            throw new ArrayListIndexOutOfBoundsException("INVALID INDEX:" + i);
        }
    }

    private void checkAndGrow() {
        if (size == elementData.length) {
            Object[] newElement = new Object[(int) (elementData.length * COEFFICIENT_GROW)];
            System.arraycopy(elementData, 0, newElement,
                    0, size);
            elementData = newElement;
        }
    }

    private void fastRemove(Object[] withoutChanges, int index) {
        final int newSize;
        if ((newSize = (size - 1)) > index) {
            System.arraycopy(withoutChanges, index + 1, withoutChanges, index, newSize - index);
        }
        withoutChanges[size = newSize] = null;
    }
}
