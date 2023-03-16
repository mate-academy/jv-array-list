package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            if (initialCapacity > 10) {
                this.elementData = new Object[initialCapacity];
            } else {
                this.elementData = new Object[DEFAULT_CAPACITY];
            }
        } else {
            throw new ArrayListIndexOutOfBoundsException("Illegal Capacity: "
                    + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
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
        rangeCheckForAdd(index);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        rangeCheckForGetRemove(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGetRemove(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGetRemove(index);
        T oldValue = (T) elementData[index];
        fastRemove(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final int size = this.size;
        int index = -1;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    index = i;
                }
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Can't remove element");
        }
        fastRemove(elementData, index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void fastRemove(Object[] es, int index) {
        rangeCheckForGetRemove(index);
        final int newSize = size - 1;
        if ((newSize) > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        es[size = newSize] = null;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index:" + (index));
        }
    }

    private void rangeCheckForGetRemove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index:" + (index));
        }
    }

    private Object[] grow() {
        int newCapacity = elementData.length + (elementData.length >> 1);
        return elementData = Arrays.copyOf(elementData,
                newCapacity);
    }
}
