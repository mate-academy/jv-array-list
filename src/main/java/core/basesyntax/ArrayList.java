package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        this.elementData = new Object[Math.max(DEFAULT_CAPACITY, initialCapacity)];
    }

    @Override
    public void add(T value) {
        add(value, size);
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
        Object[] tempArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempArray[i] = list.get(i);
        }
        int numNew = tempArray.length;
        if (numNew == 0) {
            return;
        }
        if (numNew > (elementData.length - size)) {
            elementData = grow(size + numNew);
        }
        System.arraycopy(tempArray, 0, elementData, size, numNew);
        size = size + numNew;
    }

    @Override
    @SuppressWarnings("unchecked")
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
        final Object[] newElementData = elementData;
        @SuppressWarnings("unchecked") T oldValue = (T) newElementData[index];
        size--;
        if (size > index) {
            System.arraycopy(newElementData, index + 1, newElementData, index, size - index);
        }
        newElementData[size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] newElementData = elementData;
        int i = 0;
        boolean flag = false;
        for (; i < size; i++) {
            if (element == null && newElementData[i] == null) {
                flag = true;
                break;
            } else if (element != null && element.equals(newElementData[i])) {
                flag = true;
                break;
            }
        }
        @SuppressWarnings("unchecked") T oldValue = (T) newElementData[i];
        if (!flag) {
            throw new NoSuchElementException();
        }
        remove(i);
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = (int) (oldCapacity * 1.5);
            Object[] tempArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, tempArray, 0, size);
            return elementData = tempArray;
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
}
