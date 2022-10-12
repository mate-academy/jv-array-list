package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_EXCEPTION = "Index is incorrect";
    private static final String ELEMENT_EXCEPTION = "Can't find this element: ";
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION);
        }
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size += 1;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final T[] elementDataBeforeRemove = elementData;
        T oldValue = elementDataBeforeRemove[index];
        fastRemove(elementDataBeforeRemove, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException(ELEMENT_EXCEPTION + element);
    }

    private void fastRemove(T[] elementDataBefRem, int index) {
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementDataBefRem, index + 1,
                    elementDataBefRem, index, newSize - index);
        }
        size = newSize;
        elementDataBefRem[size] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION);
        }
    }

    private void grow() {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData,0, newElementData,0, size);
            elementData = newElementData;
        }
    }
}
