package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        resize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        checkIndex(index);
        resize();
        int indexDesPos = index + 1;
        if (index == size - 1) {
            indexDesPos = index;
        }
        System.arraycopy(elementData,index,elementData,indexDesPos,size - indexDesPos);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
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
        Object oldValue = elementData[index];
        size--;
        System.arraycopy(elementData,index + 1,elementData,index,size - index);
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index is out of range");
        }
    }

    private void resize() {
        if (size >= elementData.length) {
            elementData = Arrays.copyOf(elementData,elementData.length + (elementData.length >> 1));
        }
    }
}
