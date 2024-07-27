package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_FACTOR = 2;
    private final T[] EMPTY_ELEMENT_DATA = (T[]) new Object [0];
    private T[] elementList;
    private int size;

    public ArrayList() {
        elementList = EMPTY_ELEMENT_DATA;
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elementList[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can not add element, "
                    + "index is out of bounds.");
        }
        growIfNeeded();
        System.arraycopy(elementList, index, elementList, index + 1, size - index);
        elementList[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listAsArray = (T[]) list.toArray();
        if (listAsArray.length > elementList.length - size) {
            elementList = grow(listAsArray.length + size);
        }
        System.arraycopy(listAsArray, 0, elementList, size, listAsArray.length);
        size += listAsArray.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementList[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementList[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        int newSize = size - 1;
        final T oldValue = elementList[index];
        System.arraycopy(elementList, index + 1, elementList, index, newSize - index);
        size = newSize;
        elementList[size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elementList[i])
                    || (element != null && element.equals(elementList[i]))) {
                T oldValue = elementList[i];
                remove(i);
                return oldValue;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] elementData = new Object[size];
        System.arraycopy(elementList, 0, elementData, 0, size);
        return elementData;
    }

    private T[] grow(int minCapacity) {
        int oldCapacity = elementList.length;
        if (elementList != EMPTY_ELEMENT_DATA) {
            int minGrowth = minCapacity - oldCapacity;
            int defaultGrowth = oldCapacity / GROW_FACTOR;
            int newCapacity = oldCapacity + Math.max(minGrowth, defaultGrowth);
            T[] elementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementList,0,elementData,0, elementList.length);
            return elementList = elementData;
        } else {
            return elementList = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    private void growIfNeeded() {
        if (size == elementList.length) {
            grow(size + 1);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can not remove element, "
                    + "index " + index + " is out of bounds " + "(size: " + size + ").");
        }
    }
}
