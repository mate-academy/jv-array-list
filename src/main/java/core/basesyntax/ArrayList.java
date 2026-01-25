package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    private Object[] elementData;
    private int size;

    public ArrayList() {
        size = 0;
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    @Override
    public void add(T value) {
        if (size + 1 > elementData.length) {
            elementData = grow(size + 1);
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if ((index < 0) || (index > size)) {
            throw new ArrayListIndexOutOfBoundsException("index is incorrect:" + index);
        }
        if (size + 1 > elementData.length) {
            elementData = grow(size + 1);
        }
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("Can not add list");
        }
        if (size + list.size() > elementData.length) {
            elementData = grow(size + list.size());
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is incorrect:" + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is incorrect:" + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index is incorrect:" + index);
        }
        T oldIndexElementData = (T) elementData[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return oldIndexElementData;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = -1;

        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    indexOfElement = i;
                    break;
                }
            } else {
                if (element.equals(elementData[i])) {
                    indexOfElement = i;
                    break;
                }
            }
        }

        if (indexOfElement == -1) {
            throw new NoSuchElementException("Can't find element:" + element);
        }

        T oldIndexElementData = (T) elementData[indexOfElement];

        for (int i = indexOfElement; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }

        size--;
        return oldIndexElementData;
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
        int newCapacity;

        if (oldCapacity <= 0) {
            newCapacity = DEFAULT_CAPACITY;
        } else {
            newCapacity = oldCapacity + oldCapacity / 2;
        }

        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }

        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        return newArray;
    }
}
