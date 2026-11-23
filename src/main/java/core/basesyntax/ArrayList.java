package core.basesyntax;

import java.util.Arrays;
import java.lang.Exception;

public class ArrayList<T> implements List<T> {
    transient Object[] elementData;
    int size;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    private T[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new T[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    private void add(T value, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow(size + 1);
        elementData[s] = value;
        size = s + 1;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size) {
            final int s;
            Object[] elementData;
            if ((s = size) == (elementData = this.elementData).length) {
                elementData = grow();
            }
            int i = index;
            while (i < this.elementData.length) {
                elementData[i + 1] = elementData[i];
                i++;
            }
            elementData[index] = value;
            add(value, elementData, index);
            return;
        } else {
            add(value, elementData, (size + 1));
        }
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index < elementData.length) {
            return elementData[index];
        }
        throw ArrayListIndexOutOfBoundsException("No such index");
    }

    @Override
    public void set(T value, int index) {
        if (index <= size) {
            elementData[index] = value;
        } else {
            throw ArrayListIndexOutOfBoundsException("No such index");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size) {
            int i = index;
            while ((i + 1) < elementData.length) {
                elementData[i] = elementData[i + 1];
                i++;
            }
            elementData[i] = null;
        } else {
            throw NoSuchElementException();
        }

    }

    @Override
    public T remove(T element) {
        int isFound = 0;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element) {
                this.remove(i);
                isFound = 1;
            }
        }
        if (isFound) {
            throw NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return elementData.length;
    }

    @Override
    public boolean isEmpty() {
        return elementData.length == 0;
    }
}
