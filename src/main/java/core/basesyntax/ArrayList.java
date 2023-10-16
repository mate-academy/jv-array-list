package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_ERROR_MSG = "Index out of range";
    private static final String NO_ELEMENT_ERROR_MSG = "NO such element";
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
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MSG);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (!indexCheckForExist(index, size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MSG);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        final int newSize = size - 1;
        if (!indexCheckForExist(index, size)) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MSG);
        }
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        elementData[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final int newSize;
        int index = getIndexByElement(element);
        Object removedValue = elementData[index];
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;
        return (T) removedValue;
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
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MSG);
        }
    }

    private void resizeWhenFull() {
        if (size == elementData.length) {
            Object[] oldValue = elementData;
            int oldLength = elementData.length;
            int newLength = oldLength + (Math.round((float) oldLength / 2));
            Object[] newValue = new Object[newLength];
            System.arraycopy(oldValue, 0, newValue, 0, oldValue.length);
            elementData = newValue;
        }
    }

    private int getIndexByElement(T element) {
        int index;
        for (index = 0; index < size; index++) {
            if (element == elementData[index]
                    || (element != null && element.equals(elementData[index]))) {
                break;
            }
            if (index == size - 1) {
                throw new NoSuchElementException(NO_ELEMENT_ERROR_MSG);
            }
        }
        return index;
    }
}
