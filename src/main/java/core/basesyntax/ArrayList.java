package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int NON_ADD_FUNCTION_INDEX = 1;
    private static final int GROW_SIZE_INDEX = 1;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private transient Object[] elementData = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow(size + GROW_SIZE_INDEX);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheck(index);
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow(size + GROW_SIZE_INDEX);
        }
        System.arraycopy(elementData, index,
                elementData, index + GROW_SIZE_INDEX,
                s - index);
        elementData[index] = value;
        size = s + GROW_SIZE_INDEX;
    }

    @Override
    public void addAll(List<T> list) {
        grow(list.size() + size);
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index + NON_ADD_FUNCTION_INDEX);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index + NON_ADD_FUNCTION_INDEX);
        elementData[index] = (T) value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index + NON_ADD_FUNCTION_INDEX);
        T oldValue = (T) elementData[index];
        Object[] bufferArray = elementData;
        System.arraycopy(bufferArray,
                index + GROW_SIZE_INDEX,
                elementData,
                index,
                elementData.length - index - GROW_SIZE_INDEX);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && element == null) {
                remove(i);
                return null;
            } else if (elementData[i] == null) {
                continue;
            }
            if (elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element not found");
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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length
                || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = (int) (oldCapacity * 1.5);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

}
