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
        int newSize = size + list.size();
        if (newSize > elementData.length) {
            elementData = grow();
        } else {
            for (int i = 0; i < list.size(); i++) {
                elementData[size] = list.get(i);
                size++;
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index");
        } else {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index");
        } else {
            Object[] es = elementData;
            T oldValue = (T) elementData[index];
            final int newSize = size - 1;
            if (newSize > index) {
                System.arraycopy(es, index + 1, es, index, newSize - index);
            }
            es[size = newSize] = null;
            return oldValue;
        }
    }

    @Override
    public T remove(T element) {
        final Object[] copyElements = elementData;
        int i = 0;
        foundElement :
        {
            if (element == null) {
                for (; i < size; i++) {
                    if (copyElements[i] == null) {
                        break foundElement;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (element.equals(copyElements[i])) {
                        break foundElement;
                    }
                }
            }
            throw new NoSuchElementException("There is no such element");
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
}
