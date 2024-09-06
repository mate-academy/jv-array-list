package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int OFFSET = 1;
    private static final int SIZE_ADJUSTMENT = 1;
    private static final int START_INDEX = 0;
    private static final double ELEMENT_DATA_MULTIPLIER = 1.5;
    private int listCapacity = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[listCapacity];
    }

    @Override
    public void add(T value) {
        if (size == listCapacity) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < START_INDEX) {
            checkIndex(index);
        }
        if (size == listCapacity) {
            grow();
        }
        final int s = size;
        System.arraycopy(elementData, index, elementData, index + OFFSET, s - index);
        elementData[index] = value;
        size = s + SIZE_ADJUSTMENT;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() >= listCapacity) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size += SIZE_ADJUSTMENT;
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
        final int temporaryValue = size;
        final T oldValue = elementData[index];
        index += 1;
        System.arraycopy(elementData, index, elementData, index - OFFSET, temporaryValue - index);
        size = temporaryValue - SIZE_ADJUSTMENT;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final int temporaryValue = size;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null || elementData[i].equals(element)) {
                System.arraycopy(elementData, i + OFFSET, elementData, i, temporaryValue - i);
                size = temporaryValue - SIZE_ADJUSTMENT;
                return element;
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

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementData, size));
    }

    private void grow() {
        int size = (int) (elementData.length * ELEMENT_DATA_MULTIPLIER);
        T[] expandedElementData = (T[]) new Object[size];
        System.arraycopy(elementData, START_INDEX, expandedElementData, START_INDEX, this.size);
        elementData = expandedElementData;
        listCapacity = size;
    }

    private void checkIndex(int index) {
        if (index >= size || index < START_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }
}
