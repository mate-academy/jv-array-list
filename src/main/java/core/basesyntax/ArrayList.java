package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            elementData = grow();
        } else if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index");
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
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
        Object[] copyElements = elementData;
        T oldValue = (T) elementData[index];
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(copyElements, index + 1, copyElements, index, newSize - index);
        }
        copyElements[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] copyElements = elementData;
        int i = 0;
        for (; i < size; i++) {
            if (element == null) {
                if (copyElements[i] == null) {
                    break;
                }
            } else if (element.equals(copyElements[i])) {
                break;
            }

            if (i == size - 1) {
                throw new NoSuchElementException("There is no such element");
            }
        }
        Object oldElement = elementData[i];
        int newSize = size - 1;
        if ((size - 1) > i) {
            System.arraycopy(elementData, i + 1, elementData, i, newSize - 1);
            size--;
        }
        elementData[size] = null;
        return (T) oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        int newCapacity = (int) (elementData.length * 1.5);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        return newElementData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index");
        }
    }
}
