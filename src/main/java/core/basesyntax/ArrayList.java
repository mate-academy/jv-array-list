package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeWhenFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckForAdd(index);
        resizeWhenFull();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        if (!indexCheckForExist(index, size)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        return (T)elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (!indexCheckForExist(index, size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!indexCheckForExist(index, size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
        final Object[] temp = elementData;
        T oldValue = (T) elementData[index];
        fastRemove(temp, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final int size = this.size;
        int i;
        boolean noElement = false;
        if (element == null) {
            for (i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    break;
                }
            }
        } else {
            for (i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    break;
                }
                if (i == size - 1) {
                    throw new NoSuchElementException("NO such element");
                }
            }
        }
        Object removedValue = elementData[i];
        fastRemove(elementData, i);
        return (T)removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean indexCheckForExist(int index,int size) {
        return index >= 0 && index < size;
    }

    private void indexCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }

    }

    private void fastRemove(Object[] array, int index) {
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(array, index + 1, array, index, newSize - index);
        }
        array[size = newSize] = null;
    }

    private void resizeWhenFull() {
        if (size == elementData.length) {
            Object[] oldValue = elementData;
            int oldLength = elementData.length;
            int newLength = oldLength + (Math.round((float) oldLength / 2));
            Object[] newValue = new Object[newLength];
            System.arraycopy(oldValue,0,newValue,0,oldValue.length);
            elementData = newValue;
        }
    }
}

