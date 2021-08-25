package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NON_ADD_FUNCTION_INDEX = 1;
    private static final int GROW_SIZE_INDEX = 1;
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
        Object[] elementData;
        if (size == (elementData = this.elementData).length) {
            elementData = grow(size + GROW_SIZE_INDEX);
        }
        System.arraycopy(elementData, index, elementData,
                index + GROW_SIZE_INDEX, size - index);
        elementData[index] = value;
        size++;
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
        System.arraycopy(bufferArray, index + GROW_SIZE_INDEX, elementData, index,
                elementData.length - index - GROW_SIZE_INDEX);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || (element != null && element.equals(elementData[i]))) {
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
        int newCapacity = (int) (oldCapacity * 1.5);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

}
