package core.basesyntax;

import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private int maxSize = 0;
    private Object[] elementData;

    @Override
    public void add(T value) {
        if (this.isEmpty()) {
            elementData = new Object[DEFAULT_SIZE];
            maxSize = DEFAULT_SIZE;
        } else {
            if (this.size == maxSize) {
                maxSize = maxSize + maxSize / 2;
                Object[] oldElementData = elementData;
                elementData = new Object[maxSize];
                elementData = oldElementData;
            }
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size) {
            Object[] oldElementsData = new Object[size];
            System.arraycopy(elementData, 0, oldElementsData, 0, size);
            elementData = new Object[size + 1];
            System.arraycopy(oldElementsData, 0, elementData, 0, index);
            elementData[index] = value;
            System.arraycopy(oldElementsData, index, elementData, index, size - index);
        } else {
            throw new ArrayListIndexOutOfBoundsException("ArrayList out of bound execption");
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        T oldValue = (T) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    @Override
    public T remove(T element) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }
}
