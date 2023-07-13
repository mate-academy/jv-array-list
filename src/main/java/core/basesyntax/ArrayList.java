package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            rangeCheck(index);
        }
        growIfNeeded();
        System.arraycopy(this.elementData, index, this.elementData, index + 1, size - index);
        this.elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T valueToReturn = get(index);
        fastRemove(elementData, index);
        return valueToReturn;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        int indexToDelete = returnIndexOfElement(element, es, size);
        fastRemove(es, indexToDelete);
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

    private int returnIndexOfElement(T element, Object[] es, int size) {
        int i = 0;
        if (element == null) {
            for (; i < size; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(es[i])) {
                    return i;
                }
            }
            throw new NoSuchElementException("Element you want to remove does not exit");
        }
        return i;
    }

    private void growIfNeeded() {
        if (size >= elementData.length) {
            grow();
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(createIndexOutOfBoundsMsg(index));
        }
    }

    private String createIndexOutOfBoundsMsg(int index) {
        return "Index: " + index + "out of bounds, cause size is: " + size;
    }

    private void fastRemove(Object[] elementData, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(elementData, i + 1, elementData, i, newSize - i);
        }
        elementData[size = newSize] = null;
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] oldArray = (T[]) elementData;
        elementData = (T[]) new Object[newCapacity];
        System.arraycopy(oldArray, 0, elementData, 0, oldCapacity);
    }
}
