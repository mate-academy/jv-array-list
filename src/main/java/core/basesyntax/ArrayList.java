package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
        return true;
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
        if (size + list.size() > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    private void checkIndex(int index) {
        if ((index >= size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
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
        @SuppressWarnings("unchecked") T oldValue = (T) elementData[index];
        if ((size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
        }
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int i = 0;
        found:
        {
            if (element == null) {
                for (; i < size; i++) {
                    if (elementData[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (element.equals(elementData[i])) {
                        break found;
                    }
                }
            }
            throw new NoSuchElementException("Failed remove, because no such element " + element);
        }
        return remove(i);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //////////////////////////////////////////////////////////////////////////////

    private Object[] grow() {
        Object[] growElementData = new Object[newCapacity()];
        System.arraycopy(elementData, 0,
                growElementData, 0,
                size);
        return growElementData;
    }

    private int newCapacity() {
        return size + (size >> 1);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "size="
                + size
                + ", elementData="
                + Arrays.toString(elementData)
                + '}';
    }
}
