package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != new Object[DEFAULT_CAPACITY]) {
            int newCapacity = oldCapacity + (int) (oldCapacity * 1.5);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void checkBoundsIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void fastRemove(int index) {
        size--;
        Object[] tmpArray = new Object[size];
        System.arraycopy(elementData, 0, tmpArray, 0, index);
        System.arraycopy(elementData, index + 1, tmpArray, index, size - index);
        elementData = tmpArray;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = value;
        size += 1;
    }

    @Override
    public void add(T value, int index) {
        checkBoundsIndex(index);
        Object[] elementData = this.elementData;
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
        System.arraycopy(elementData, index, elementData, index + 1,size - index);
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
        T oldValue = (T) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        boolean noSuchElement = true;
        T oldValue = null;
        for (int i = 0; i < size; i++) {
            if ((element == elementData[i]) || (element != null
                    && element.equals(elementData[i]))) {
                oldValue = (T) elementData[i];
                noSuchElement = false;
                fastRemove(i);
                break;
            }
        }
        if (noSuchElement) {
            throw new NoSuchElementException("No such element");
        } else {
            return oldValue;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
