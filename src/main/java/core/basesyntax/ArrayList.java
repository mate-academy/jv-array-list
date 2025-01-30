package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    private transient Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void grow(int minCapacity) {
        int newCapacity = elementData.length + (elementData.length >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }

        if (newCapacity > Integer.MAX_VALUE - 8) {
            newCapacity = Integer.MAX_VALUE - 8;
        }

        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }

        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            ensureCapacityInternal(size + 1);
        }

        elementData[size++] = value;

    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds" + index);
        }

        ensureCapacityInternal(size + 1);

        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = value;

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        int numNew = list.size();

        ensureCapacityInternal(size + numNew);

        for (int i = 0; i < numNew; i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound" + index);
        }

        T elementDatum = (T) elementData[index];
        return elementDatum;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound" + index);
        }

        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound" + index);
        }

        final T removedElement = (T) elementData[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData [i + 1];
        }

        elementData[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found " + element);
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
