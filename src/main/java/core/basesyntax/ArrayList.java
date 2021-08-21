package core.basesyntax;

import jdk.internal.util.ArraysSupport;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    transient Object[] elementData = new Object[10];

    @Override
    public void add(T value) {
        if (size == elementData.length) grow(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheck(index);
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow(size + 1);
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
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
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = (T) value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T oldValue = (T) elementData[index];
        Object[] bufferArray = elementData;
        System.arraycopy(bufferArray,
                index + 1,
                elementData,
                index,
                elementData.length - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if(elementData[i] == element) remove(i);
            return element;
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

    private void rangeCheck(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0)
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = (int) (oldCapacity * 1.5);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

}
