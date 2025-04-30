package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREMENT_VALUE = 1.5;

    private transient Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity;
        if (oldCapacity * INCREMENT_VALUE > minCapacity) {
            newCapacity = (int) (oldCapacity * INCREMENT_VALUE);
        } else {
            newCapacity = (int) (minCapacity * INCREMENT_VALUE);
        }
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        return elementData = newArray;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect " + index);
        }
    }

    public Object[] toArray(List<T> list) {
        if (list != null) {
            Object[] array = new Object[list.size()];
            for (int i = 0; i < array.length; i++) {
                array[i] = list.get(i);
            }
            return array;
        }
        throw new RuntimeException("Given object cannot be converted to an array");
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect " + index);
        }
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elementData).length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = value;
        size = s + 1;
    }

    @Override
    public void add(T value) {
        add(value, elementData, size);
    }

    private void add(T value, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] array = toArray(list);
        int numNew = array.length;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(array, 0, elementData, s, numNew);
        size = s + numNew;
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
        final Object[] es = elementData;

        T oldValue = (T) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
        boolean found = false;

        if (element == null) {
            for (; i < size; i++) {
                if (es[i] == null) {
                    found = true;
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (element.equals(es[i])) {
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            throw new NoSuchElementException("Element not found");
        }
        fastRemove(es, i);
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
}
