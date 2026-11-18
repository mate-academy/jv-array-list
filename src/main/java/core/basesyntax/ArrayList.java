package core.basesyntax;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    transient Object[] elementData;
    int size;
    float grow;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public int checkIndex() {
        return (elementData.lenght - 1);
    }

    @Override
    public Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity,
                    oldCapacity >> 1);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    @Override
    public void add(T value) {
        modCount++;
        add(value, elementData, size);
    }

    private void add(T value, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = value;
        size = s + 1;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        oldCapacity = elementData.length;
    }

    @Override
    public void add(T value, int index) {
        modCount++;
        add(e, elementData, size);
        return
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        if (index < checkIndex()) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        elementData[index] = null;
        int i = index;
        while(elementData.[i + 1] < elementData.length){
            elementData[i] = element[i + 1];
            i++;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element) {
                this.remove(i);
            }
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
